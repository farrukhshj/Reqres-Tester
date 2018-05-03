package shuja.com.reqrestester.network.facade;

import java.io.IOException;

import shuja.com.reqrestester.model.CreateUserDTO;
import shuja.com.reqrestester.model.UserEntity;
import shuja.com.reqrestester.network.service.UserService;
import shuja.com.reqrestester.utility.Callback;
import shuja.com.reqrestester.utility.NoNetworkException;
import shuja.com.reqrestester.utility.Task;
import shuja.com.reqrestester.utility.WebException;

public class UserEntityFacade {

    static public void getUsers(final Callback<UserEntity> callback) {
        new Task<>(new Task.RunInBackground<UserEntity>() {
            @Override
            public UserEntity runInBackground() throws NoNetworkException, WebException, IOException {
                return UserService.getUsersFromEndPoint();
            }
        }, callback).execute();
    }

    public static void addUser(final CreateUserDTO createUserDTO, Callback<String> callback) {
        new Task<>(new Task.RunInBackground<String>() {
            @Override
            public String runInBackground() throws Exception {
                return UserService.postUserToEndPoint(createUserDTO);
            }
        }, callback).execute();
    }
}
