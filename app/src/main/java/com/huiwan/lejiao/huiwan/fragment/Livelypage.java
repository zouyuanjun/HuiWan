package com.huiwan.lejiao.huiwan.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huiwan.lejiao.huiwan.DataBean.Chardata;
import com.huiwan.lejiao.huiwan.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by zou on 2018/3/28.
 */

public class Livelypage extends Fragment {
    private LineChartView lineChart;
    List<Chardata> list=new ArrayList<>();
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_livelypage,container,false);
        lineChart=rootview.findViewById(R.id.mychar);
        setdata();
        getAxisPoints(list);
        getAxisPoints2(list2);
        getAxisXLables(list);
        initLineChart();
        return rootview;
    }
    List<Chardata> list2=new ArrayList<>();


    private List<PointValue> mPointValues2 = new ArrayList<PointValue>();
    public void setdata(){
        Chardata chartData=new Chardata("20","15");
        Chardata chartData1=new Chardata("21","20");
        Chardata chartData2=new Chardata("22","15");
        Chardata chartData3=new Chardata("23","8");
        Chardata chartData4=new Chardata("24","44");
        Chardata chartData5=new Chardata("25","15");
        Chardata chartData6=new Chardata("26","30");
        Chardata chartData7=new Chardata("27","65");
        Chardata chartData8=new Chardata("28","75");
        list.add(chartData);
        list.add(chartData1);
        list.add(chartData2);
        list.add(chartData3);
        list.add(chartData4);
        list.add(chartData5);
        list.add(chartData6);
        list.add(chartData7);
        list.add(chartData8);
        list2.add(chartData3);
        list2.add(chartData4);
        list2.add(chartData8);
        list2.add(chartData6);
        list2.add(chartData4);
        list2.add(chartData6);
        list2.add(chartData6);
        list2.add(chartData3);
        list2.add(chartData7);
        list2.add(chartData1);

    }
    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables( List<Chardata> list) {
        for (int i = 0; i < list.size(); i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(list.get(i).getDate()));
        }
    }
    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints(List<Chardata> list) {
        for (int i = 0; i < list.size(); i++) {
            float point=Float.parseFloat(list.get(i).getPoint());
            mPointValues.add(new PointValue(i, point));
        }
    }

    private void getAxisPoints2(List<Chardata> list) {
        for (int i = 0; i < list.size(); i++) {
            float point=Float.parseFloat(list.get(i).getPoint());
            mPointValues2.add(new PointValue(i, point));
        }
    }
    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFe6dc"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        // line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line.setPointRadius(2);

        Line line2 = new Line(mPointValues2).setColor(Color.parseColor("#3ca0f5"));  //折线的颜色（橙色
        line2.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line2.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line2.setFilled(false);//是否填充曲线的面积
        line2.setHasLabels(true);//曲线的数据坐标是否加上备注
        // line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line2.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line2.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line2.setPointRadius(2);

        lines.add(line);
        lines.add(line2);
        LineChartData data = new LineChartData();
        data.setLines(lines);


        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        axisX.setName("");  //表格名称
        axisX.setTextSize(15);//设置字体大小
        axisX.setMaxLabelChars(5); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //  data.setAxisXTop(axisX);  //x 轴在顶部
        //  axisX.setHasLines(true); //x 轴分割线
        //  Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        //  axisY.setName("净值");//y轴标注
        axisY.setTextColor(Color.BLACK);
        axisY.setHasLines(true);
        //  axisY.setTextSize(15);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setScrollEnabled(true);
        lineChart.setMaxZoom((float) 3);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 1000;
        lineChart.setCurrentViewport(v);
    }
}
