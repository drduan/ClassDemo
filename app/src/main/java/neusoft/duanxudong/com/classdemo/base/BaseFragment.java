/*-
 * Authors      : harry <luhe1987@gmail.com>
 *
 * Created Date : Aug 28, 2014
 *  
 * Mojob Limited Inc.  All rights reserved.
 *
 */

package neusoft.duanxudong.com.classdemo.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

public class BaseFragment extends Fragment {

    String requestId;
    // Hold the activity reference to void getActivity() returns null.
    private Activity mActivity;
    private OnClickListener clickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {

            // Forward this event to a
            // central handler
            onUserInteraction(v);
        }
    };

    public String getRequestId() {
        return requestId;
    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        super.setHasOptionsMenu(true);
//
//        // Register event bus
//        Notify.Action.register(this);
//        Notify.Data.register(this);
//        Notify.Exception.register(this);
//        Notify.Logout.register(this);
//
//        this.requestId = OkHttpHelper.genernateRequestId(this);
//
//    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }


//    @Override
//    public void onDestroy() {
//
//        super.onDestroy();
//
//        // Unregister event bus
//        Notify.Action.unregister(this);
//        Notify.Data.unregister(this);
//        Notify.Exception.unregister(this);
//        Notify.Logout.unregister(this);
//
//
//
//        mActivity = null;
//    }

    public OnClickListener getClickListener() {

        return clickListener;
    }

    public void onUserInteraction(View view) {

    }

//    public void log(Object obj) {
//
//        // You can use filter *** to filter out message
//        LoggingUtils.error(((Object) this).getClass().getName(),
//                String.format("*** %s ***",
//                        obj == null ? "--!--"
//                                : obj.toString()));
//    }


    // ------------------------------------
    // ListView helper methods
    // ------------------------------------

    /**
     * Add empty view to list view
     */
    public synchronized void addEmptyView(ListView listView) {

        if (listView == null) {
            return;
        }

        if (listView.getEmptyView() != null) {
            return;
        }

//        try {
//            View emptyView = getBaseActivity().getLayoutInflater().inflate(R.layout.viewlet_empty_view, null);
//            emptyView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//            emptyView.setVisibility(View.GONE);
//            ((ViewGroup) listView.getParent()).addView(emptyView);
//            listView.setEmptyView(emptyView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Remove empty view from list view
     *
     * @param listView
     */
    public synchronized void removeEmptyView(ListView listView) {

        if (listView == null) {
            return;
        }

        try {
            View emptyView = listView.getEmptyView();
            if (emptyView != null) {
                ((ViewGroup) listView.getParent()).removeView(emptyView);
                listView.setEmptyView(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return {@link BaseActivity} that holds the fragment.
     */
    public BaseActivity getBaseActivity() {

        if (mActivity != null) {
            return (BaseActivity) mActivity;
        }

        return (BaseActivity) getActivity();
    }

    // ------------------------------------
    // EventBus methods
    // ------------------------------------
//    public void onEventMainThread(Action action) {
//
//    }




    // ------------------------------------
    // Other methods
    // ------------------------------------
//    public PreferenceManager getPreferenceManager() {
//        return PreferenceManager.getInstance(getActivity().getApplicationContext());
//    }

}
