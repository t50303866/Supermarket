package cn.tzl.shop.framgent;

import cn.bmob.v3.BmobQuery;
import cn.tzl.shop.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CommunityFragment extends BaseFragment{

	@Override
	public View createSuccessView() {
		Log.i("CommunityFragment",""+ 22222222);
		TextView view = new TextView(getActivity());
		view.setText("我是社区");
		return view;
		
	}



	

}
