package cn.tzl.shop.framgent;

import cn.tzl.shop.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InformationFragment extends BaseFragment{

	@Override
	public View createSuccessView() {
		Log.i("HomeFragment",""+ 33333);
		TextView view = new TextView(getActivity());
		view.setText("我是消息页");
		return view;
	}




}
