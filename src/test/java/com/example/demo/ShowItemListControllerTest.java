package com.example.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.ShowItemListController;
import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ShowItemListService;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ShowItemListControllerTest {

	private static MockHttpServletRequest request;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CategoryRepository categoryRepository;

	@MockBean
	private ShowItemListService showItemListServiceMock; //この記述によりServiceMockの戻り値を指定できるようになる。
	
	@Autowired
	private ShowItemListController showItemListController; //おそらくこの記述で依存性の注入を行っている。
	
	@Autowired
	private ItemRepository itemRepository;

	public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

	@BeforeAll
	public static void setup() {
		request = new MockHttpServletRequest();
		request.setParameter("name", "MLB");
		request.setParameter("category", "3");
		request.setParameter("brand", "47");
	}

	@Test
	@DisplayName("商品検索のテスト")
	@WithMockUser(username = "田中", password = "Maeatu12")
	public void postShowItemListHttpRequest() throws Exception {
		Integer category = 3;
		Category smallCategory = categoryRepository.findIdSmallCategory(category);
		List<Item> itemList = itemRepository.findByNameAndCategoryAndBrand("MLB", 3, "47");
		when(showItemListServiceMock.showIdSmallCategoryList(category)).thenReturn(smallCategory);
        
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.param("name", request.getParameterValues("name"))
				.param("category", request.getParameterValues("category"))
				.param("brand", request.getParameterValues("brand"))).andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		ModelAndViewAssert.assertViewName(mav, "list");
	}

	@Test
	@DisplayName("中カテゴリ情報取得のテスト")
	@WithMockUser(username = "田中", password = "Maeatu12")
	public void getLargeCategory() throws Exception {

		List<Category> mediumCategoryList = categoryRepository.findByParentIdMediumCategory(1);				
		assertFalse(mediumCategoryList.isEmpty());
		when(showItemListServiceMock.showParentIdMediumCategoryList(1)).thenReturn(mediumCategoryList);
		
		mockMvc.perform(post("/largeCategory").contentType(MediaType.APPLICATION_JSON).param("largeCategoryId", "1"))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.mediumCategoryList", hasSize(14)));	
		
		verify(showItemListServiceMock,times(1)).showParentIdMediumCategoryList(1);
		
	}
	
	
	

}
