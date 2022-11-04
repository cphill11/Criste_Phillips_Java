package com.trilogyed.catalogconfig.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.catalogconfig.service.CatalogServiceLayer;
import com.trilogyed.catalogconfig.viewModel.ConsoleViewModel;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // Service layer mocked to test controller & NOT service layer
    @MockBean
    private CatalogServiceLayer storeServiceLayer;

    @Autowired
    //used to move between Objects and JSON
    private ObjectMapper mapper;
    @Test
    public void shouldReturnNewConsoleOnPostRequest() throws Exception {
        //Mock "in"coming Console...
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(12);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("199.89"));

        //Mock "out"put Console...
        ConsoleViewModel outConsole = new ConsoleViewModel();
        outConsole.setMemoryAmount("250GB");
        outConsole.setQuantity(12);
        outConsole.setManufacturer("Sega");
        outConsole.setModel("Nintendo");
        outConsole.setProcessor("AMD");
        outConsole.setPrice(new BigDecimal("199.89"));
        outConsole.setId(15);

        // Mocking service layer's method "createConsoleViewModel"
        when(this.storeServiceLayer.createConsole(inConsole)).thenReturn(outConsole);

        mockMvc.perform (
                post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(outConsole)));
    }

    @Test
    public void shouldReturnConsoleById() throws Exception{
        //Arrange
        //Mock "out"put Console...
        ConsoleViewModel outConsole = new ConsoleViewModel();
        outConsole.setMemoryAmount("250GB");
        outConsole.setQuantity(12);
        outConsole.setManufacturer("Sega");
        outConsole.setModel("Nintendo");
        outConsole.setProcessor("AMD");
        outConsole.setPrice(new BigDecimal("199.89"));
        outConsole.setId(15);

        // Mocking service layer to test controllers
        when(storeServiceLayer.getConsoleById(15)).thenReturn(outConsole);

        //Act & Assert
        mockMvc.perform( MockMvcRequestBuilders
                .get("/console/{id}", 15)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(15));
    }

    @Test
    public void shouldReturn204StatusWithGoodUpdate() throws Exception {
        //Mock "in"coming Console...
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("300GB");
        inConsole.setQuantity(12);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo II");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("249.99"));
        inConsole.setId(15);

        // Mocking service layer to test controllers
        doNothing().when(storeServiceLayer).updateConsole(inConsole);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn404StatusWithBadIdUpdateRequest() throws Exception {
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("300GB");
        inConsole.setQuantity(12);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo II");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("249.99"));
        inConsole.setId(0);

        //mock call to controller and force an exception
        doThrow(new IllegalArgumentException("Console not found. Unable to update")).when(storeServiceLayer).updateConsole(inConsole);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteConsoleReturnNoContent() throws Exception{
        // Mocking service layer to test controllers
        doNothing().when(storeServiceLayer).deleteConsole(15);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/console/{id}",15))
                .andDo(print())
                .andExpect(status().isNoContent()); //Expected response status code.
    }

    @Test
    public void shouldReturnConsoleByManufacturer() throws Exception {

        List<ConsoleViewModel> consoleViewModelList = new ArrayList<>();

        //Mock a list of Consoles...

        //1st Console...
        ConsoleViewModel outConsole1 = new ConsoleViewModel();
        outConsole1.setMemoryAmount("250GB");
        outConsole1.setQuantity(12);
        outConsole1.setManufacturer("Sony");
        outConsole1.setModel("PS4");
        outConsole1.setProcessor("AMD");
        outConsole1.setPrice(new BigDecimal("499.89"));
        outConsole1.setId(15);

        consoleViewModelList.add(outConsole1);

        //2nd Console...
        outConsole1 = new ConsoleViewModel();
        outConsole1.setMemoryAmount("200GB");
        outConsole1.setQuantity(12);
        outConsole1.setManufacturer("Sony");
        outConsole1.setModel("PS2");
        outConsole1.setProcessor("AMD");
        outConsole1.setPrice(new BigDecimal("249.99"));
        outConsole1.setId(16);

        consoleViewModelList.add(outConsole1);

        // Mocking service layer to test controllers
        when(storeServiceLayer.getConsoleByManufacturer("Sony")).thenReturn(consoleViewModelList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/console/manufacturer/{manufacturer}", "Sony")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(consoleViewModelList)));
    }

    @Test
    public void shouldReturnAllConsoles() throws Exception {

        List<ConsoleViewModel> consoleViewModelList = new ArrayList<>();

        //Mock a list of Consoles...

        //1st Console...
        ConsoleViewModel outConsole1 = new ConsoleViewModel();
        outConsole1.setMemoryAmount("250GB");
        outConsole1.setQuantity(12);
        outConsole1.setManufacturer("Sony");
        outConsole1.setModel("PS4");
        outConsole1.setProcessor("AMD");
        outConsole1.setPrice(new BigDecimal("499.89"));
        outConsole1.setId(15);

        consoleViewModelList.add(outConsole1);

        //2nd Console...
        outConsole1 = new ConsoleViewModel();
        outConsole1.setMemoryAmount("200GB");
        outConsole1.setQuantity(12);
        outConsole1.setManufacturer("Sony");
        outConsole1.setModel("PS2");
        outConsole1.setProcessor("AMD");
        outConsole1.setPrice(new BigDecimal("249.99"));
        outConsole1.setId(16);

        consoleViewModelList.add(outConsole1);

        // Mocking service layer to test controllers
        when(storeServiceLayer.getAllConsoles()).thenReturn(consoleViewModelList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/console")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(consoleViewModelList)));

        when(storeServiceLayer.getAllConsoles()).thenReturn(null);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/console")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    //Testing bad cases
    @Test
    public void shouldFailCreateConsoleWithInvalidQuantity() throws Exception {
        //Mock "in"coming Console  with 0 quantity
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(51000);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("199.89"));

        // Mocking service layer to test controllers
        when(this.storeServiceLayer.createConsole(inConsole)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Mock "in"coming Console  with > 50,000 quantity
        inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(50001);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("199.89"));

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailCreateConsoleWithInvalidPrice() throws Exception {
        //Mock "in"coming Console  with null price
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(null);

        // Mocking service layer to test controllers
        when(this.storeServiceLayer.createConsole(inConsole)).thenReturn(null);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Mock "in"coming Console  with no price
        inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("1000.00"));

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Mock "in"coming Console  with no price
        inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(BigDecimal.ZERO);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;
    }

    @Test
    public void shouldFailCreateConsoleInvalidManufacturer() throws Exception {
        //Mock "in"coming Console  with 0 quantity
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer(null);
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("10.99"));

        // Mocking service layer to test controllers
        when(this.storeServiceLayer.createConsole(inConsole)).thenReturn(null);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;
    }

    @Test
    public void shouldFailCreateConsoleInvalidModel() throws Exception {
        //Mock "in"coming Console  with 0 quantity
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("10.99"));

        // Mocking service layer to test controllers
        when(this.storeServiceLayer.createConsole(inConsole)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Mock "in"coming Console  with 0 quantity
        inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel(null);
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("10.99"));

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.post("/console")
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;
    }

    @Test
    public void shouldFailUpdateConsoleInvalidModel() throws Exception {
        //Mock "in"coming Console  with 0 quantity
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("10.99"));
        inConsole.setId(15);

        // Mocking service layer to test controllers
        doNothing().when(this.storeServiceLayer).updateConsole(inConsole);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Mock "in"coming Console  with 0 quantity
        inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel(null);
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("10.99"));
        inConsole.setId(15);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;
    }

    @Test
    public void shouldFailUpdateConsoleInvalidQuantity() throws Exception {
        //Mock "in"coming Console  with 0 quantity
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(0);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("199.89"));
        inConsole.setId(15);

        // Mocking service layer to test controllers
        doNothing().when(this.storeServiceLayer).updateConsole(inConsole);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Mock "in"coming Console  with > 50,000 quantity
        inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(50001);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("199.89"));

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;
    }

    @Test
    public void shouldFailUpdateConsoleInvalidPrice() throws Exception {
        //Mock "in"coming Console  with null price
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(null);
        inConsole.setId(15);

        // Mocking service layer to test controllers
        doNothing().when(this.storeServiceLayer).updateConsole(inConsole);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Mock "in"coming Console  with no price
        inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("1000.00"));
        inConsole.setId(15);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;

        //Mock "in"coming Console  with no price
        inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer("Sega");
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(BigDecimal.ZERO);
        inConsole.setId(15);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
        ;
    }

    @Test
    public void shouldFailUpdateConsoleInvalidManufacturer() throws Exception {
        //Mock "in"coming Console  with 0 quantity
        ConsoleViewModel inConsole = new ConsoleViewModel();
        inConsole.setMemoryAmount("250GB");
        inConsole.setQuantity(2);
        inConsole.setManufacturer(null);
        inConsole.setModel("Nintendo");
        inConsole.setProcessor("AMD");
        inConsole.setPrice(new BigDecimal("10.99"));
        inConsole.setId(15);

        // Mocking service layer to test controllers
        doNothing().when(this.storeServiceLayer).updateConsole(inConsole);

        //ResultActions x = mockMvc.perform(
        mockMvc.perform(
                MockMvcRequestBuilders.put("/console/{id}", 15)
                        .content(mapper.writeValueAsString(inConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
            .andExpect(status().isUnprocessableEntity())
        ;
    }
    @Test
    public void shouldFailGetConsoleWithBadId() throws Exception{
        //Mock "out"put Console...
        ConsoleViewModel outConsole = new ConsoleViewModel();
        outConsole.setMemoryAmount("250GB");
        outConsole.setQuantity(12);
        outConsole.setManufacturer("Sega");
        outConsole.setModel("Nintendo");
        outConsole.setProcessor("AMD");
        outConsole.setPrice(new BigDecimal("199.89"));
        outConsole.setId(15);

        // Mocking service layer to test controllers
        when(storeServiceLayer.getConsoleById(16)).thenReturn(null);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/console/{id}", 16)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

    }
    @Test
    public void shouldFailGetConsoleByManufacturerWithInvalidManufacturer() throws Exception {

        //no need to create dummy data since we are returning null anyway.
        List<ConsoleViewModel> consoleViewModelList = new ArrayList<>();

        // Mocking service layer to test controllers
        when(storeServiceLayer.getConsoleByManufacturer("Sony")).thenReturn(null);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/console/manufacturer/{manufacturer}", "Sony")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }
}