package piratehat.appstore.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.githang.statusbar.StatusBarCompat;

import butterknife.BindView;
import piratehat.appstore.R;
import piratehat.appstore.adapter.ViewPagerAdapter;
import piratehat.appstore.fragment.RefreshAppsFragment;

public class CategoryDetailsActivity extends BaseActivity {

    @BindView(R.id.tl_navigation)
    TabLayout mTlNavigation;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    private String mCategory;
    private String[] mDetails;
    private String mSelectedCategory;


    @Override
    protected int setResId() {
        return R.layout.activity_category_details;
    }

    @Override
    protected void initData(Bundle bundle) {
        mCategory = bundle.getString("category");
        mDetails = bundle.getStringArray("details");
        mSelectedCategory= bundle.getString("select");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorBackgroundWhite), true);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        mToolBar.setTitle(mCategory);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(RefreshAppsFragment.newInstance(mCategory));
        for (int i = 0; i < mDetails.length; i++) {
            viewPagerAdapter.addFragment(RefreshAppsFragment.newInstance(mDetails[i]));
        }

        mVpContent.setAdapter(viewPagerAdapter);
        mTlNavigation.setupWithViewPager(mVpContent);

        mTlNavigation.getTabAt(0).setText("全部");
        for (int i = 0; i < mDetails.length; i++) {
           mTlNavigation.getTabAt(i+1).setText(mDetails[i]);
        }


    }


}
