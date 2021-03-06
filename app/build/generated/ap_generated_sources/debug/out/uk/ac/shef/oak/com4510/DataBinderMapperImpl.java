package uk.ac.shef.oak.com4510;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import uk.ac.shef.oak.com4510.databinding.ActivityMain2BindingImpl;
import uk.ac.shef.oak.com4510.databinding.ActivityMainBindingImpl;
import uk.ac.shef.oak.com4510.databinding.ActivityShowVisitDataBindingImpl;
import uk.ac.shef.oak.com4510.databinding.ActivityUpdateVisitBindingImpl;
import uk.ac.shef.oak.com4510.databinding.VisitRowBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_ACTIVITYMAIN2 = 2;

  private static final int LAYOUT_ACTIVITYSHOWVISITDATA = 3;

  private static final int LAYOUT_ACTIVITYUPDATEVISIT = 4;

  private static final int LAYOUT_VISITROW = 5;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(5);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(uk.ac.shef.oak.com4510.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(uk.ac.shef.oak.com4510.R.layout.activity_main2, LAYOUT_ACTIVITYMAIN2);
    INTERNAL_LAYOUT_ID_LOOKUP.put(uk.ac.shef.oak.com4510.R.layout.activity_show_visit_data, LAYOUT_ACTIVITYSHOWVISITDATA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(uk.ac.shef.oak.com4510.R.layout.activity_update_visit, LAYOUT_ACTIVITYUPDATEVISIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(uk.ac.shef.oak.com4510.R.layout.visit_row, LAYOUT_VISITROW);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN2: {
          if ("layout/activity_main2_0".equals(tag)) {
            return new ActivityMain2BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main2 is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSHOWVISITDATA: {
          if ("layout/activity_show_visit_data_0".equals(tag)) {
            return new ActivityShowVisitDataBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_show_visit_data is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYUPDATEVISIT: {
          if ("layout/activity_update_visit_0".equals(tag)) {
            return new ActivityUpdateVisitBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_update_visit is invalid. Received: " + tag);
        }
        case  LAYOUT_VISITROW: {
          if ("layout/visit_row_0".equals(tag)) {
            return new VisitRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for visit_row is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(5);

    static {
      sKeys.put("layout/activity_main_0", uk.ac.shef.oak.com4510.R.layout.activity_main);
      sKeys.put("layout/activity_main2_0", uk.ac.shef.oak.com4510.R.layout.activity_main2);
      sKeys.put("layout/activity_show_visit_data_0", uk.ac.shef.oak.com4510.R.layout.activity_show_visit_data);
      sKeys.put("layout/activity_update_visit_0", uk.ac.shef.oak.com4510.R.layout.activity_update_visit);
      sKeys.put("layout/visit_row_0", uk.ac.shef.oak.com4510.R.layout.visit_row);
    }
  }
}
