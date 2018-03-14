package com.gooner10.ifactortest.user;

import com.gooner10.ifactortest.model.Users;
import com.gooner10.ifactortest.network.ApiService;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.List;

import retrofit2.Callback;


/**
 * Test for {@link UserPresenter}
 */
public class UserPresenterTest {
    @Mock
    UserContract.View view;

    @Mock
    ApiService apiService;

    @Captor
    ArgumentCaptor<Callback<List<Users>>> argumentCaptor;

    private UserPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new UserPresenter(view);
    }

}