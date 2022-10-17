package shalini.mvvm.vsco.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.content.Context;

import androidx.databinding.Observable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import shalini.mvvm.vsco.model.Hit;


@RunWith(MockitoJUnitRunner.class)
public class VSCOItemViewModelTest {

    private static final String IMAGE_URL = "https://cdn.pixabay.com/photo/2022/10/13/07/54/crane-houses-7518536_150.jpg";
    private static final String USER_NAME = "flickrOrig";
    private static final String USER_IAMGE = "https://cdn.pixabay.com/user/2020/07/25/21-10-11-80_250x250.jpg";

    private Context context = mock(Context.class);

    @Test
    public void isUserNameValid() {
        Hit hit = new Hit();
        hit.setUser(USER_NAME);
        VSCOItemViewModel vscoItemViewModel = givenVscoItemViewModel(hit);
        assertEquals(hit.getUser(), vscoItemViewModel.getName());
    }


    @Test
    public void isImageThumbnailValid() {
        Hit hit = new Hit();
        hit.setPreviewURL(IMAGE_URL);
        VSCOItemViewModel vscoItemViewModel = givenVscoItemViewModel(hit);
        assertEquals(hit.getPreviewURL(), vscoItemViewModel.getThumbnail());
    }

    @Test
    public void isUserImageValid() {
        Hit hit = new Hit();
        hit.setPreviewURL(USER_IAMGE);
        VSCOItemViewModel vscoItemViewModel = givenVscoItemViewModel(hit);
        assertEquals(hit.getUserImageURL(), vscoItemViewModel.getProfileImage());
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetPeople() {
        Hit hit = new Hit();
        VSCOItemViewModel vscoItemViewModel = givenVscoItemViewModel(hit);
        Observable.OnPropertyChangedCallback mockCallback = mock(Observable.OnPropertyChangedCallback.class);
        vscoItemViewModel.addOnPropertyChangedCallback(mockCallback);
        vscoItemViewModel.setHit(hit);

        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }

    private VSCOItemViewModel givenVscoItemViewModel(Hit hit) {
        return new VSCOItemViewModel(hit);
    }
}
