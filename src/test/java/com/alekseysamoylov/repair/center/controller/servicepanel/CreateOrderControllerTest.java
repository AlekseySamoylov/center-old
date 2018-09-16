package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.AbstractTest;
import org.junit.Ignore;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by alekseysamoylov on 7/26/16.
 */
@Ignore
@ContextConfiguration({"classpath:dispatcher-servlet.xml", "classpath:spring-security.xml"})
public class CreateOrderControllerTest extends AbstractTest {

//    @InjectMocks
//    CreateOrderController createOrderController;
//
//    private PrepareUser prepareUser;
//    private MockMvc mockMvc;
//
//    @Mock
//    FillClientList fillClientList;
//
//    @Mock
//    FillWebList<WebClientCar> fillClientCarList;
//
//    @Mock
//    FillWebList<WebCarMark> fillCarMarkList;
//
//    @Mock
//    WebOrderStorage webOrderStorage;
//
//    @Mock
//    View mockView;
//
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void before() {
//        MockitoAnnotations.initMocks(this);
//        prepareUser = new PrepareUser();
//        mockMvc = MockMvcBuilders.standaloneSetup(createOrderController)
//                .setSingleView(mockView)
//                .build();
//    }
//
//    @Test
//    public void isManager() throws Exception {
//        createOrderController = new CreateOrderController(fillClientList, fillCarMarkList, webOrderStorage,fillClientCarList);
//        prepareUser.setRole("ROLE_MANAGER");
//        Assert.assertTrue(createOrderController.isManager());
//    }

//    @Test
//    public void goSelectClient() throws Exception {
//        createOrderController = new CreateOrderController(fillClientList, fillCarMarkList, webOrderStorage,fillClientCarList);
//
//        prepareUser.setRole("ROLE_MANAGER");
//        WebOrderTemplate webOrderTemplate = new WebOrderTemplate();
//        webOrderTemplate.setManagerId(1L);
//        webOrderTemplate.setCompanyId(1L);
//        List<WebClient> webClients = new ArrayList<>();
//        WebClient client = new WebClient();
//        client.setId(1L);
//        client.setName("Vasiliy");
//        webClients.add(client);
//        Mockito.when(fillClientList.getFilledWebList(-1L).getWebList()).thenReturn(webClients);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/service-panel/create")).andExpect(status().isOk());
////        Assert.assertEquals(MockMvcResultMatchers.status().isOk(), createOrderController.goSelectClient());
//    }
//
//    @Test
//    public void goCreateClient() throws Exception {
////        mockMvc.perform(MockMvcRequestBuilders.post("/"))
//
//    }
//
//    @Test
//    public void goCreateCar() throws Exception {
//
//    }
//
//    @Test
//    public void goCreateText() throws Exception {
//
//    }
//
//    @Test
//    public void saveNewOrder() throws Exception {
//
//    }

}