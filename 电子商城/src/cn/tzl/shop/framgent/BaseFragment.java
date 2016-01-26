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
			init();//��frameLayout�м��ز���
		}else{
			ViewUtils.removeParent(frameLayout);
		}
		
		showPage();//���ݲ�ͬ��״̬��ʾ��ͬ�Ľ���
		show();//���ݷ������������л�״̬
		return frameLayout;
	}

	

	private View loadingView;//�����е�ҳ��
	private View errorView;//���ش������
	private View emptyView;//���ؿս���
	private View successView;//���سɹ��Ľ���
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
