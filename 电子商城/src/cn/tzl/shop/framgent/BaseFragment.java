package cn.tzl.shop.framgent;

import cn.bmob.v3.Bmob;
import cn.tzl.shop.viewUtils.ViewUtils;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public abstract class BaseFragment extends Fragment {
	private FrameLayout frameLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(frameLayout == null){
			frameLayout = new FrameLayout(getActivity());
			init();//在frameLayout中加载布局
		}else{
			ViewUtils.removeParent(frameLayout);
		}
		
		showPage();//根据不同的状态显示不同的界面
		show();//根据服务器的数据切换状态
		return frameLayout;
	}

	

	private View loadingView;//加载中的页面
	private View errorView;//加载错误界面
	private View emptyView;//加载空界面
	private View successView;//加载成功的界面
	private void init() {
		successView = createSuccessView();
		frameLayout.addView(successView, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
	}
	

	public abstract View createSuccessView();


	private void showPage() {
		// TODO Auto-generated method stub
		
	}
	private void show() {
		// TODO Auto-generated method stub
		
	}
}
