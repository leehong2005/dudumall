package com.example.fwqi.dudu.page;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwqi.dudu.Manager.LoginManager;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.adapter.CartListAdapter;
import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.data.CartCheckData;
import com.example.fwqi.dudu.data.CartData;
import com.example.fwqi.dudu.data.ServiceData;
import com.example.fwqi.dudu.utils.HttpUtils;
import com.example.fwqi.dudu.widget.CartListFooterView;
import com.example.fwqi.dudu.widget.CartListHeaderView;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CartFragmentPage extends ActionBarFragment {
    private RelativeLayout cartView = null;
    private ListView cartListView = null;
    private CartListHeaderView listHeaderView = null;
    private CartListFooterView listFooterView = null;
    private CartListAdapter cartListAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().addLoginChangedListener(new CartOnLoginChangedListener());
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        if (cartView == null) {
            cartView = (RelativeLayout) inflater.inflate(R.layout.cart_fragment_page, container, false);

            // Add the list header view.
            cartListView = (ListView) cartView.findViewById(R.id.cart_list_view);
            listHeaderView = new CartListHeaderView(cartView.getContext());
            cartListView.addHeaderView(listHeaderView);

            // Add the list footer view.
            listFooterView = new CartListFooterView(cartView.getContext());
            cartListView.addFooterView(listFooterView);

            // Add the list item view.
            cartListAdapter = new CartListAdapter(inflater.getContext());
            cartListView.setAdapter(cartListAdapter);

            this.setTitleName("购物车");
            this.hideLoadingView();
        }

        if (!LoginManager.getInstance().isLogin()) {
            showLoginPromptView();
        }else {
            // Start the request work to get cart check data from server.
            CartRequestThread cartRequestThread = new CartRequestThread(new CartUIHandler());
            cartRequestThread.start();
        }
        return cartView;
    }

    protected void refreshContentView() {
        // Start the request work to get cart check data from server.
        CartRequestThread cartRequestThread = new CartRequestThread(new CartUIHandler());
        cartRequestThread.start();
    }

    /**
     * Show the login prompt view.
     */
    private void showLoginPromptView() {
        View loginPromptView = LayoutInflater.from(
                cartView.getContext()).inflate(R.layout.login_prompt_view, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        cartView.addView(loginPromptView, layoutParams);
    }

    /**
     * Hide the login prompt view.
     */
    private void hideLoginPromptView() {
        View loginPromptView = cartView.findViewById(R.id.login_prompt_view);
        cartView.removeView(loginPromptView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class CartOnLoginChangedListener implements LoginManager.OnLoginChangedListener {
        @Override
        public void onLoginChanged(int loginStatus) {
            if (loginStatus == AppConstants.LOGIN_SUCCESS) {
                hideLoginPromptView();
            }else if(loginStatus == AppConstants.LOGIN_OUT) {
                showLoginPromptView();
            }
            refreshContentView();
        }
    }

    class CartUIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ServiceData cartCheckData = (ServiceData) msg.obj;
            if (cartCheckData != null) {
                if (cartCheckData.getErrNo() == AppConstants.ERROR_CODE_SUCCESS) {
                    // Set the cart info from local memory.
                    if (cartView != null && cartListAdapter != null) {
                        cartListAdapter.setDataList(CartData.getInstance());
                    }

                    // Set the cart check info from server.
                    TextView productsAmountView = (TextView) cartView.findViewById(R.id.products_amount);
                    TextView deliverAmountView = (TextView) cartView.findViewById(R.id.deliver_amount);
                    TextView paidAmountView = (TextView) cartView.findViewById(R.id.paid_amount);

                    CartCheckData cartCheck = (CartCheckData) cartCheckData.getData("cart_check");
                    if (cartCheck != null) {
                        productsAmountView.setText("$" + cartCheck.getProductsPrice());
                        deliverAmountView.setText("$" + cartCheck.getDeliverPrice());
                        paidAmountView.setText("$" + cartCheck.getNeedPay());

                        if (!TextUtils.isEmpty(cartCheck.getPayMsg())) {
                            listHeaderView.showFreeDeliverMsg();
                        } else {
                            listHeaderView.hideFreeDeliverMsg();
                        }
                    }
                } else {
                    // Show error message.
                    switch (cartCheckData.getErrNo()) {
                        case AppConstants.ERROR_CODE_UNLOGIN:
                            if (cartView != null) {
                                showLoginPromptView();
                            }
                            Toast.makeText(CartFragmentPage.this.getActivity(),
                                    cartCheckData.getErrNo() + cartCheckData.getErrMsg(),
                                    Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(CartFragmentPage.this.getActivity(),
                                    cartCheckData.getErrNo() + cartCheckData.getErrMsg(),
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        }
    }

    /**
     * CartRequestThread class is used for getting cart check data from server.
     */
    class CartRequestThread extends Thread {
        private Handler uiHandler = null;

        public CartRequestThread(Handler uiHandler) {
            this.uiHandler = uiHandler;
        }

        @Override
        public void run() {
            super.run();
            if (uiHandler != null) {
                Message message = uiHandler.obtainMessage();
                message.obj = HttpUtils.getCartCheckData();
                message.sendToTarget();
            }
        }
    }
}
