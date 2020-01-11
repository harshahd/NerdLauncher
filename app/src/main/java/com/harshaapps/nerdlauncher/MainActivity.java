package com.harshaapps.nerdlauncher;

import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity {
        @Override
        public Fragment createFragment() {
return NerdLauncherFragment.newInstance();
        }
}
