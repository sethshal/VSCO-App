package shalini.mvvm.vsco.viewmodel;

import android.content.Context;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import shalini.mvvm.vsco.data.VSCOService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class VSCOViewModelTest {

    private Context mockContext = mock(Context.class);

    @Mock
    private VSCOService vscoService;

    @InjectMocks
    private VSCOViewModel vscoViewModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    @Before public void setUpMainViewModelTest() {
        vscoViewModel = new VSCOViewModel(vscoService);
    }

    @Test
    public void testNull() {
        when(vscoService.fetchHits(false, "andffkfk")).thenReturn(null);
        assertEquals(vscoViewModel.getHitList().size(),0);
    }

    @Test public void ensureTheViewsAreInitializedCorrectly() {
        vscoViewModel.initializeViews();
        assertTrue(vscoViewModel.hitsLabel.getValue().equals(false));
        assertTrue(vscoViewModel.hitsProgress.getValue().equals(true));
        assertTrue(vscoViewModel.hitRecycler.getValue().equals(false));
    }
}
