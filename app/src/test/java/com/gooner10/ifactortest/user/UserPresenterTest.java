package com.gooner10.ifactortest.user;

import com.gooner10.ifactortest.model.Users;
import com.gooner10.ifactortest.network.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


/**
 * Test for {@link UserPresenter}
 */
public class UserPresenterTest {
    @Mock
    UserContract.View view;

    @Mock
    ApiService apiService;

    @Mock
    Call<List<Users>> mockCall;

    @Mock
    ResponseBody responseBody;

    @Captor
    ArgumentCaptor<Callback<List<Users>>> argumentCaptor;

    private UserPresenter presenter;
    private List<Users> usersList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new UserPresenter(view);
        usersList = Collections.singletonList(new Users());
    }

    @Test
    public void loadUserData_displaysUserData_whenResponseIsSuccessful() throws Exception {
        when(apiService.getUserData()).thenReturn(mockCall);
        Response<List<Users>> listResponse = Response.success(usersList);

        presenter.loadUserData(apiService);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, listResponse);

        verify(view).displayUserData(usersList);
    }

    @Test
    public void loadUserData_displayError_whenResponseFails() throws Exception {
        when(apiService.getUserData()).thenReturn(mockCall);
        Response<List<Users>> listResponse = Response.error(500, responseBody);

        presenter.loadUserData(apiService);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, listResponse);

        verifyZeroInteractions(view);
    }
}