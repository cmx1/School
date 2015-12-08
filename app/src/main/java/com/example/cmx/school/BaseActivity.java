package com.example.cmx.school;

import android.app.Activity;

public abstract class  BaseActivity extends Activity{
	@Override
	    public void setContentView(int layoutResID) {
	        super.setContentView(layoutResID);
	        findView();
	        initView();
	        setOnClick();
	    }
	   /**
	     * ��ȡ���ֿؼ�
	     */
	    protected abstract void findView();

	    /**
	     * ��ʼ��View��һЩ����
	     */
	    protected abstract void initView();

	    /**
	     * ���õ������
	     */
	    protected abstract void setOnClick();
}
