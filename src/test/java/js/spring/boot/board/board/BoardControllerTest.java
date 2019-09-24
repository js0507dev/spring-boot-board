package js.spring.boot.board.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import js.spring.boot.board.board.controller.BoardController;
import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.service.BoardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BoardControllerTest {
  private BoardController subject;

  @Autowired
  private BoardService boardService;

  @Autowired
  private ObjectMapper objectMapper;

  private MockMvc mockMvc;
  private MockHttpServletRequest request;
  private ModelAndView mv;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    subject = new BoardController(boardService);

    request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    mv = new ModelAndView();

    mockMvc = MockMvcBuilders.standaloneSetup(subject)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();

    Board response = Board.builder()
            .id(1L)
            .title("테스트 제목")
            .content("테스트용 컨텐트")
            .writerId("test")
            .build();
    Board response2 = Board.builder()
            .id(2L)
            .title("test 22 제목")
            .content("test 22 컨텐트")
            .writerId("test")
            .build();
    List<Board> boards = new ArrayList<>();
    boards.add(response);
    boards.add(response2);
    Page<Board> findAllResponse = new PageImpl<>(boards);

    given(boardService.findById(eq(1L))).willReturn(response);
    given(boardService.findAll(any())).willReturn(findAllResponse);
    //given(boardService.saveBoard(any(Board.class))).willReturn(response);
    //given(boardService.updateBoard(eq(1L),any(Board.class))).willReturn(response);
  }

  @Test
  public void shouldReturnBoardList() throws Exception {
    mockMvc.perform(get("/board")
                    .param("page", "0")
                    .param("size", "20")
                    .param("sort", "id, desc")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..*").exists())
            .andDo(print());
  }

  @Test
  public void shouldReturnSelectOne() throws Exception {
    mockMvc.perform(get("/board/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("board"))
            .andDo(print());
  }

  @Test
  public void shouldSaveBoard() throws Exception {
    // TODO: 로그인없이 왜 되는거지?
    mockMvc.perform(post("/board")
                    .param("title","test")
                    .param("content","test")
                    .param("writerId","test")
    )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.content").exists())
            .andDo(print());
  }

  @Test
  public void shouldUpdateBoard() throws Exception {
    // TODO: 로그인없이 왜 되는거지?
    mockMvc.perform(put("/board/{id}", 1L)
            .param("id","1")
            .param("title","test2")
            .param("content","test2")
            .param("writerId","test")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").exists())
            .andDo(print());
  }

  @Test
  public void shouldDeleteBoard() throws Exception {
    // TODO: 로그인없이 왜 되는거지?
    mockMvc.perform(delete("/board/{id}", 1L))
            .andExpect(status().isOk())
            .andDo(print());
  }
}
