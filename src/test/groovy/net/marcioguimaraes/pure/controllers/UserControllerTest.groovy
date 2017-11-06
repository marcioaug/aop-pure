package net.marcioguimaraes.pure.controllers

import net.marcioguimaraes.pure.AOPApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.http.MockHttpOutputMessage
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

import java.nio.charset.Charset

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner)
@SpringBootTest(classes = AOPApplication)
@WebAppConfiguration
class UserControllerTest {

    private MediaType contentType = new  MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName('utf8'))

    private MockMvc mockMvc

    @Autowired
    private WebApplicationContext webApplicationContext

    @Before
    void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build()
    }

    @Test
    void userGreeting() throws Exception {
        mockMvc.perform(get('/users/greeting').contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath('$.name', is("Marcio Augusto")))
                .andExpect(jsonPath('$.id', notNullValue()))
    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get('/users/1'))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath('$.id', is(1)))
    }

    @Test
    void saveUser() {
        mockMvc.perform(post('/users')
                .content('''
                    {
                        "name": "Fulano de Tal",
                        "username": "fulano"
                    }
                ''')
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath('$.name', is("Fulano de Tal")))
                .andExpect(jsonPath('$.id', notNullValue()))
    }

}
