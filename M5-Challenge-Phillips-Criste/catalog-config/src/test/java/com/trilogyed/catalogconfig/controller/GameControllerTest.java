package com.trilogyed.catalogconfig.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.catalogconfig.service.CatalogServiceLayer;

import com.trilogyed.catalogconfig.viewModel.GameViewModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Service layer mocked to test controller & NOT service layer
    @MockBean
    private CatalogServiceLayer storeServiceLayer;

    @Autowired
    //used to move between Objects and JSON
    private ObjectMapper mapper;

    @Test
    public void shouldCreateGame() throws Exception{
        //Object to JSON in String
        String outputJson = null;
        String inputJson=null;

        //Arrange
        GameViewModel inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);

        inputJson = mapper.writeValueAsString(inGameViewModel);
        System.out.println(inputJson);

        GameViewModel savedGameViewModel = new GameViewModel();
        savedGameViewModel.setTitle("Halo");
        savedGameViewModel.setEsrbRating("E10+");
        savedGameViewModel.setDescription("Puzzles and Math");
        savedGameViewModel.setPrice(new BigDecimal("23.99"));
        savedGameViewModel.setStudio("Xbox Game Studios");
        savedGameViewModel.setQuantity(5);
        savedGameViewModel.setId(51);

        outputJson = mapper.writeValueAsString(savedGameViewModel);

        //Mock call to service layer...
        when(storeServiceLayer.createGame(inGameViewModel)).thenReturn(savedGameViewModel);

        //Act & Assert
        this.mockMvc.perform(post("/game")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnGameInfo() throws Exception{

        //Object to JSON in String
        String outputJson = null;

        //Arrange
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setTitle("Halo");
        gameViewModel.setEsrbRating("E10+");
        gameViewModel.setDescription("Puzzles and Math");
        gameViewModel.setPrice(new BigDecimal("23.99"));
        gameViewModel.setStudio("Xbox Game Studios");
        gameViewModel.setQuantity(5);
        gameViewModel.setId(8);

        outputJson = mapper.writeValueAsString(gameViewModel);

        // Mocking DAO response
         when(storeServiceLayer.getGame(8)).thenReturn(gameViewModel);

        //Act & Assert
        this.mockMvc.perform(get("/game/8"))
                .andDo(print())
                .andExpect(status().isOk())
                //use the objectmapper output with the json method
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldFailGetGameBadIdReturns404() throws Exception {

        String outputJson = null;
        long idForGameThatDoesNotExist = 100;

        //Arrange
        GameViewModel gameViewModel = new GameViewModel();
        outputJson = mapper.writeValueAsString(gameViewModel);
        when(storeServiceLayer.getGame(idForGameThatDoesNotExist)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/game/" + idForGameThatDoesNotExist))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateGame() throws Exception{
        //Object to JSON in String
        String inputJson=null;

        //Arrange
        GameViewModel inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(62);

        inputJson = mapper.writeValueAsString(inGameViewModel);

        //Mock call to service layer; nothing to mock
        //Checking for the correct response status code
        doNothing().when(storeServiceLayer).updateGame(inGameViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/game/{id}", 62)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteGame() throws Exception{
        //Object to JSON in String
        String inputJson=null;

        //Arrange
        GameViewModel inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(62);

        inputJson = mapper.writeValueAsString(inGameViewModel);

        //Mock call to service layer; nothing to mock
        //Checking for the correct response status code
        doNothing().when(storeServiceLayer).deleteGame(62);

        //Act & Assert
        this.mockMvc.perform(delete("/game/62"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetGamesByTitle() throws Exception{
        //Object to JSON in String
        String outputJson = null;

        //Arrange
        GameViewModel savedGameViewModel1 = new GameViewModel();
        savedGameViewModel1.setTitle("Halo");
        savedGameViewModel1.setEsrbRating("E10+");
        savedGameViewModel1.setDescription("Puzzles and Math");
        savedGameViewModel1.setPrice(new BigDecimal("23.99"));
        savedGameViewModel1.setStudio("Xbox Game Studios");
        savedGameViewModel1.setQuantity(5);
        savedGameViewModel1.setId(56);

        GameViewModel savedGameViewModel2 = new GameViewModel();
        savedGameViewModel2.setTitle("Halo I");
        savedGameViewModel2.setEsrbRating("E10+");
        savedGameViewModel2.setDescription("Puzzles and Math");
        savedGameViewModel2.setPrice(new BigDecimal("23.99"));
        savedGameViewModel2.setStudio("Xbox Game Studios");
        savedGameViewModel2.setQuantity(5);
        savedGameViewModel2.setId(51);

        GameViewModel savedGameViewModel3 = new GameViewModel();
        savedGameViewModel3.setTitle("Halo IV");
        savedGameViewModel3.setEsrbRating("E10+");
        savedGameViewModel3.setDescription("Puzzles and Math");
        savedGameViewModel3.setPrice(new BigDecimal("23.99"));
        savedGameViewModel3.setStudio("Xbox Game Studios");
        savedGameViewModel3.setQuantity(5);
        savedGameViewModel3.setId(77);

        List<GameViewModel> foundList = new ArrayList();
        foundList.add(savedGameViewModel1);

        outputJson = mapper.writeValueAsString(foundList);

        //Mock call to service layer...
        when(storeServiceLayer.getGameByTitle("Halo")).thenReturn(foundList);

        //Act & Assert
        this.mockMvc.perform(get("/game/title/Halo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getGameByTitle("not there")).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/game/title/{title}}","not there"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetGamesByEsrbRating() throws Exception{
        //Object to JSON in String
        String outputJson = null;

        //Arrange
        GameViewModel savedGameViewModel1 = new GameViewModel();
        savedGameViewModel1.setTitle("Halo");
        savedGameViewModel1.setEsrbRating("E10+");
        savedGameViewModel1.setDescription("Puzzles and Math");
        savedGameViewModel1.setPrice(new BigDecimal("23.99"));
        savedGameViewModel1.setStudio("Xbox Game Studios");
        savedGameViewModel1.setQuantity(5);
        savedGameViewModel1.setId(56);

        GameViewModel savedGameViewModel2 = new GameViewModel();
        savedGameViewModel2.setTitle("Halo I");
        savedGameViewModel2.setEsrbRating("E10+");
        savedGameViewModel2.setDescription("Puzzles and Math");
        savedGameViewModel2.setPrice(new BigDecimal("23.99"));
        savedGameViewModel2.setStudio("Xbox Game Studios");
        savedGameViewModel2.setQuantity(5);
        savedGameViewModel2.setId(51);

        GameViewModel savedGameViewModel3 = new GameViewModel();
        savedGameViewModel3.setTitle("Halo IV");
        savedGameViewModel3.setEsrbRating("E18+");
        savedGameViewModel3.setDescription("Puzzles and Math");
        savedGameViewModel3.setPrice(new BigDecimal("23.99"));
        savedGameViewModel3.setStudio("Xbox Game Studios");
        savedGameViewModel3.setQuantity(5);
        savedGameViewModel3.setId(77);

        List<GameViewModel> foundList = new ArrayList();
        foundList.add(savedGameViewModel1);
        foundList.add(savedGameViewModel2);

        outputJson = mapper.writeValueAsString(foundList);

        //Mock call to service layer...
        when(storeServiceLayer.getGameByEsrb("10+")).thenReturn(foundList);

        //Act & Assert
        this.mockMvc.perform(get("/game/esrbrating/10+"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getGameByEsrb("not there")).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/game/esrbrating/{esrb}", "not there"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetGamesByStudio() throws Exception{
        //Object to JSON in String
        String outputJson = null;

        //Arrange
        GameViewModel savedGameViewModel1 = new GameViewModel();
        savedGameViewModel1.setTitle("Halo");
        savedGameViewModel1.setEsrbRating("E10+");
        savedGameViewModel1.setDescription("Puzzles and Math");
        savedGameViewModel1.setPrice(new BigDecimal("23.99"));
        savedGameViewModel1.setStudio("A&E");
        savedGameViewModel1.setQuantity(5);
        savedGameViewModel1.setId(56);

        GameViewModel savedGameViewModel2 = new GameViewModel();
        savedGameViewModel2.setTitle("Halo I");
        savedGameViewModel2.setEsrbRating("E10+");
        savedGameViewModel2.setDescription("Puzzles and Math");
        savedGameViewModel2.setPrice(new BigDecimal("23.99"));
        savedGameViewModel2.setStudio("Xbox Game Studios");
        savedGameViewModel2.setQuantity(5);
        savedGameViewModel2.setId(51);

        GameViewModel savedGameViewModel3 = new GameViewModel();
        savedGameViewModel3.setTitle("Halo IV");
        savedGameViewModel3.setEsrbRating("E18+");
        savedGameViewModel3.setDescription("Puzzles and Math");
        savedGameViewModel3.setPrice(new BigDecimal("23.99"));
        savedGameViewModel3.setStudio("A&E");
        savedGameViewModel3.setQuantity(5);
        savedGameViewModel3.setId(77);

        List<GameViewModel> foundList = new ArrayList();
        foundList.add(savedGameViewModel1);
        foundList.add(savedGameViewModel3);

        outputJson = mapper.writeValueAsString(foundList);

        //Mock call to service layer...
        when(storeServiceLayer.getGameByStudio("A&E")).thenReturn(foundList);

        //Act & Assert
        this.mockMvc.perform(get("/game/studio/A&E"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getGameByStudio("not there")).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/game/studio/{A&E}","not there"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetAllGames() throws Exception{
        //Object to JSON in String
        String outputJson = null;

        //Arrange
        GameViewModel savedGameViewModel1 = new GameViewModel();
        savedGameViewModel1.setTitle("Halo");
        savedGameViewModel1.setEsrbRating("E10+");
        savedGameViewModel1.setDescription("Puzzles and Math");
        savedGameViewModel1.setPrice(new BigDecimal("23.99"));
        savedGameViewModel1.setStudio("A&E");
        savedGameViewModel1.setQuantity(5);
        savedGameViewModel1.setId(56);

        GameViewModel savedGameViewModel2 = new GameViewModel();
        savedGameViewModel2.setTitle("Halo I");
        savedGameViewModel2.setEsrbRating("E10+");
        savedGameViewModel2.setDescription("Puzzles and Math");
        savedGameViewModel2.setPrice(new BigDecimal("23.99"));
        savedGameViewModel2.setStudio("Xbox Game Studios");
        savedGameViewModel2.setQuantity(5);
        savedGameViewModel2.setId(51);

        GameViewModel savedGameViewModel3 = new GameViewModel();
        savedGameViewModel3.setTitle("Halo IV");
        savedGameViewModel3.setEsrbRating("E18+");
        savedGameViewModel3.setDescription("Puzzles and Math");
        savedGameViewModel3.setPrice(new BigDecimal("23.99"));
        savedGameViewModel3.setStudio("A&E");
        savedGameViewModel3.setQuantity(5);
        savedGameViewModel3.setId(77);

        List<GameViewModel> foundList = new ArrayList();
        foundList.add(savedGameViewModel1);
        foundList.add(savedGameViewModel3);

        outputJson = mapper.writeValueAsString(foundList);

        //Mock call to service layer...
        when(storeServiceLayer.getAllGames()).thenReturn(foundList);

        //Act & Assert
        this.mockMvc.perform(get("/game"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getAllGames()).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/game"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldFailCreateGameWithInvalidData() throws Exception {
        //Arrange
        // Title
        GameViewModel inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);

        // Mocking service layer to test controllers
        when(this.storeServiceLayer.createGame(inGameViewModel)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Esrb
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


        //Description
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Studio
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("");
        inGameViewModel.setQuantity(5);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Price
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("-1.00"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("60000"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(null);
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //quantity
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(0);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(50001);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/game")
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailUpdateGameWithInvalidData() throws Exception {
        //Arrange
        GameViewModel inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(77);

        // Mocking service layer to test controllers
        when(this.storeServiceLayer.createGame(inGameViewModel)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Esrb...
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(77);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


        //Description
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(77);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Studio...
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(77);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
        //Price...
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("-1.00"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(77);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("60000"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(77);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(null);
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(77);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //quantity
        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(0);
        inGameViewModel.setId(77);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("Halo");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(50001);
        inGameViewModel.setId(77);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/game/{id}", 77)
                        .content(mapper.writeValueAsString(inGameViewModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailFindGamesWithInvalidData() throws Exception {
        //Arrange
        //invalid id...
        GameViewModel inGameViewModel = new GameViewModel();
        inGameViewModel.setTitle("something");
        inGameViewModel.setEsrbRating("E10+");
        inGameViewModel.setDescription("Puzzles and Math");
        inGameViewModel.setPrice(new BigDecimal("23.99"));
        inGameViewModel.setStudio("Xbox Game Studios");
        inGameViewModel.setQuantity(5);
        inGameViewModel.setId(77);

        // Mocking service layer to test controllers
        when(this.storeServiceLayer.getGame(77)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/game/77")
                        .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());

        String badValue="bad value";
        // Mocking service layer to test controllers
        when(this.storeServiceLayer.getGameByStudio(badValue)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/game/studio/{badValue}",badValue)
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        when(this.storeServiceLayer.getGameByEsrb(badValue)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/game/esrbrating/{badValue}",badValue)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());


        when(this.storeServiceLayer.getGameByTitle(badValue)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/game/title/{badValue}",badValue)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
}