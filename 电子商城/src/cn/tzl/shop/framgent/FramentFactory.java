package cn.tzl.shop.framgent;

import java.util.HashMap;
import java.util.Map;

import android.support.v4.app.Fragment;

public class FramentFactory {

	private static Map<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();
	public static Fragment createFragment(int position){
		BaseFragment fragment = null;
		fragment = mFragments.get(position);
		if(fragment == null){
			if(position == 0){
				fragment = new HomeFragment();	
			}else if(position == 1){
				fragment = new CommunityFragment();
			}else if(position == 2){
				fragment = new InformationFragment();
			}else if(position == 3){
				fragment = new ShopVehicleFragment();
			}else{
				fragment = new MeFragment();
			}
			if(fragment != null){
				mFragments.put(position, fragment);
			}
		}
		
		
		return fragment;
		
	}
}
