package com.farukyilmaz.ar;

import com.farukyilmaz.ar.controllers.RestController;
import com.farukyilmaz.ar.models.Address;
import com.farukyilmaz.ar.services.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApApplicationTests {
    @Autowired
    private RestController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getAllAddress() {
        assertThat(controller.getAllAddress()).isNotNull();
    }

    //save address
    @Test
    public void saveAddress() throws Exception{

        // given - precondition or setup
        Address address = Address.builder().addressId(1L).city("City1").district("District1").neighborhood("Neighborhood1").street("Street1")
                .addressLine1("Address line 1 1").addressLine2("Address line 2 1").description("Description1").build();

        given(addressService.save(any(Address.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/address/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(address)));

        // then - verify the output
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.city",
                        is(address.getCity())))
                .andExpect(jsonPath("$.district",
                        is(address.getDistrict())))
                .andExpect(jsonPath("$.neighborhood",
                        is(address.getNeighborhood())))
                .andExpect(jsonPath("$.street",
                        is(address.getStreet())))
                .andExpect(jsonPath("$.addressLine1",
                        is(address.getAddressLine1())))
                .andExpect(jsonPath("$.addressLine2",
                        is(address.getAddressLine2())))
                .andExpect(jsonPath("$.description",
                        is(address.getDescription())));

    }

    // save address list
    @Test
    public void saveAddressList() throws Exception{

        ArrayList<Address> addressList = new ArrayList<>();
        addressList.add(Address.builder().city("City1").district("District1").neighborhood("Neighborhood1").street("Street1")
                .addressLine1("Address line 1 1").addressLine2("Address line 2 1").description("Description1").build());
        addressList.add(Address.builder().city("City2").district("District2").neighborhood("Neighborhood2").street("Street2")
                .addressLine1("Address line 1 2").addressLine2("Address line 2 2").description("Description2").build());
        given(addressService.getList()).willReturn(addressList);

        ResultActions response = mockMvc.perform(get("/api/address"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(addressList.size())));

    }

    // positive scenario - GET address by valid address id
    @Test
    public void givenAddressId_whenGetAddressById_thenReturnAddressObject() throws Exception{
        long addressId = 1L;

        Address address = Address.builder().city("City1").district("District1").neighborhood("Neighborhood1").street("Street1")
                .addressLine1("Address line 1 1").addressLine2("Address line 2 1").description("Description1").build();
        given(addressService.findById(addressId)).willReturn(address);

        ResultActions response = mockMvc.perform(get("/api/address/{id}", addressId));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.city",
                        is(address.getCity())))
                .andExpect(jsonPath("$.district",
                        is(address.getDistrict())))
                .andExpect(jsonPath("$.neighborhood",
                        is(address.getNeighborhood())))
                .andExpect(jsonPath("$.street",
                        is(address.getStreet())))
                .andExpect(jsonPath("$.addressLine1",
                        is(address.getAddressLine1())))
                .andExpect(jsonPath("$.addressLine2",
                        is(address.getAddressLine2())))
                .andExpect(jsonPath("$.description",
                        is(address.getDescription())));
    }

    // negative scenario - GET address by invalid address id
    @Test
    public void givenInvalidAddressId_whenGetAddressById_thenReturnEmpty() throws Exception{

        long addressId = 1L;

        ResultActions response = mockMvc.perform(get("/api/address/{id}", addressId));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // positive scenario - update address
    @Test
    public void givenUpdatedAddress_whenUpdateAddress_thenReturnUpdateAddressObject() throws Exception{
        long addressId = 1L;
        Address savedAddress = Address.builder().city("City1").district("District1").neighborhood("Neighborhood1").street("Street1")
                .addressLine1("Address line 1 1").addressLine2("Address line 2 1").description("Description1").build();

        Address updatedAddress=Address.builder().city("City2").district("District2").neighborhood("Neighborhood2").street("Street2")
                .addressLine1("Address line 1 2").addressLine2("Address line 2 2").description("Description2").build();

        given(addressService.findById(addressId)).willReturn(savedAddress);
        given(addressService.save(any(Address.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/address/{id}", addressId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedAddress)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.city",
                        is(updatedAddress.getCity())))
                .andExpect(jsonPath("$.district",
                        is(updatedAddress.getDistrict())))
                .andExpect(jsonPath("$.neighborhood",
                        is(updatedAddress.getNeighborhood())))
                .andExpect(jsonPath("$.street",
                        is(updatedAddress.getStreet())))
                .andExpect(jsonPath("$.addressLine1",
                        is(updatedAddress.getAddressLine1())))
                .andExpect(jsonPath("$.addressLine2",
                        is(updatedAddress.getAddressLine2())))
                .andExpect(jsonPath("$.description",
                        is(updatedAddress.getDescription())));
    }

    // negative scenario - - update address
    @Test
    public void givenUpdatedAddress_whenUpdateAddress_thenReturn404() throws Exception{
        long addressId = 1L;

        Address updatedAddress=Address.builder().addressId(1L).city("City2").district("District2").neighborhood("Neighborhood2").street("Street2")
                .addressLine1("Address line 1 2").addressLine2("Address line 2 2").description("Description2").build();

        ResultActions response = mockMvc.perform(put("/api/address/{id}", addressId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedAddress)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // delete address
    @Test
    public void givenAddress_whenDeleteAddress_thenReturn200() throws Exception{
        // given - precondition or setup
        Address address = Address.builder().addressId(1L).city("City1").district("District1").neighborhood("Neighborhood1").street("Street1")
                .addressLine1("Address line 1 1").addressLine2("Address line 2 1").description("Description1").build();

        willDoNothing().given(addressService).delete(address);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/address/{id}", address.getAddressId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
