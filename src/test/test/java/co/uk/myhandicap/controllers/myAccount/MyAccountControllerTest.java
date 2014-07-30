package test.java.co.uk.myhandicap.controllers.myAccount;

import main.java.co.uk.myhandicap.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * My Account Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/config/testContext.xml",
        "classpath:/main/webapp/WEB-INF/MyHandicapApp-servlet.xml"})
@WebAppConfiguration
public class MyAccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void myAccountControllerPersonalInformationRequestMapping() throws Exception {

        mockMvc.perform(get("/myAccount/personalInformation"))
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount/personal"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/personal.jsp"))
                .andExpect(model().attributeExists("user"));

    }

    /**
     * Case: Personal Information form submission for all fields is successful
     * Result:
     *      .hasNoErrors()
     * View:
     *      "myAccount/personal" with model attribute 'status'
     *
     * @throws Exception
     */
    @Test
    public void submitAllPersonalInformationFormInputFields() throws Exception {

        mockMvc.perform(post("/myAccount/personalInformation/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Test")
                .param("lastName", "McTester")
                .param("email", "test@test.com")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount/personal"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/personal.jsp"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attribute("success", "Personal Information Successfully Updated."));

    }

    /**
     * Case: Submitted firstName is not valid (numbers), validation should fail and error message presented to the user.
     * Result:
     *      .hasErrors(1)
     * View:
     *      "myAccount/personal" with errors amd model attribute 'failure'
     *
     * @throws Exception
     */
    @Test
    public void submitPersonalInformationAndFirstNameDigitsInvalidShouldFailValidation() throws Exception {

        mockMvc.perform(post("/myAccount/personalInformation/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "12345")
                .param("lastName", "McTester")
                .param("email", "test@test.com")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount/personal"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/personal.jsp"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(model().attribute("failure", "Personal Information Update Failed, correct errors and try again."));

     }

    /**
     * Case: Submitted firstName is not valid (minimum length), validation should fail and error message presented to the user.
     * Result:
     *      .hasErrors(1)
     * View:
     *      "myAccount/personal" with errors amd model attribute 'failure'
     *
     * @throws Exception
     */
    @Test
    public void submitPersonalInformationAndFirstNameMinLengthInvalidShouldFailValidation() throws Exception {

        mockMvc.perform(post("/myAccount/personalInformation/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "A")
                        .param("lastName", "McTester")
                        .param("email", "test@test.com")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount/personal"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/personal.jsp"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(model().attribute("failure", "Personal Information Update Failed, correct errors and try again."));

    }

    /**
     * Case: Submitted lastName is not valid (numbers), validation should fail and error message presented to the user.
     * Result:
     *      .hasErrors(1)
     * View:
     *      "myAccount/personal" with errors amd model attribute 'failure'
     *
     * @throws Exception
     */
    @Test
    public void submitPersonalInformationAndLastNameDigitsInvalidShouldFailValidation() throws Exception {

        mockMvc.perform(post("/myAccount/personalInformation/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Test")
                        .param("lastName", "349982")
                        .param("email", "test@test.com")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount/personal"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/personal.jsp"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(model().attribute("failure", "Personal Information Update Failed, correct errors and try again."));

    }

    /**
     * Case: For submitted without values, validation should fail and error message presented to the user.
     * Result:
     *      .hasErrors(3)
     * View:
     *      "myAccount/personal" with errors amd model attribute 'failure'
     *
     * @throws Exception
     */
    @Test
    public void submitFormWithoutAnyValuesValidationShouldFailValidation() throws Exception {

        mockMvc.perform(post("/myAccount/personalInformation/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("email", "")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount/personal"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/personal.jsp"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(6))
                .andExpect(model().attribute("failure", "Personal Information Update Failed, correct errors and try again."));

    }

    /**
     * Case: Form submitted with an invalid email value, validation should fail and error message presented to the user.
     * Result:
     *      .hasErrors(1)
     * View:
     *      "myAccount/personal" with errors amd model attribute 'failure'
     *
     * @throws Exception
     */
    @Test
    public void submitFormWithoutAValidEmailValuesValidationShouldFailValidation() throws Exception {

        mockMvc.perform(post("/myAccount/personalInformation/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Test")
                        .param("lastName", "McTester")
                        .param("email", "testing1234")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount/personal"))
                .andExpect(forwardedUrl("/WEB-INF/views/myAccount/personal.jsp"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(model().attribute("failure", "Personal Information Update Failed, correct errors and try again."));

    }

}