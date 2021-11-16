package csd.cs203project.tablelayout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import csd.cs203project.model.TableLayout;
import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;
import csd.cs203project.service.tablelayout.TableLayoutServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TableLayoutServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TableLayoutServiceImpl tableLayoutService;

    @Test
    void generateTableLayout_NotEnoughHeightSpace_ReturnNull() {
        TableLayout tableLayout = new TableLayout(5,6,6,6, 1);

        ArrayList<ArrayList<HashMap<String, Double>>> layout = tableLayoutService.generateTableLayout(tableLayout);

        assertNull(layout);


    }

    @Test
    void generateTableLayout_NotEnoughWidthSpace_ReturnNull() {
        TableLayout tableLayout = new TableLayout(5,6,5,7, 1);

        ArrayList<ArrayList<HashMap<String, Double>>> layout = tableLayoutService.generateTableLayout(tableLayout);

        assertNull(layout);


    }

    @Test 
    void generateTableLayout_EnoughSpace_ReturnLayout() {
        TableLayout tableLayout = new TableLayout(5000,4000,7,8, 1);

        ArrayList<ArrayList<HashMap<String, Double>>> layout = tableLayoutService.generateTableLayout(tableLayout);

        assertNotNull(layout);
    }
}
