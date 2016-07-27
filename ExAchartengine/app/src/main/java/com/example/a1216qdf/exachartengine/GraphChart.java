package com.example.a1216qdf.exachartengine;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

/**
 * Created by 1216QDF on 7/27/2016.
 */
public class GraphChart {
    public GraphicalView mChart;

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurSer;
    private XYSeriesRenderer mCurRen;
    private ArrayList<Float[]> addXY = new ArrayList<Float[]>();

    GraphChart(String axiz , Context context){
        mCurSer = new XYSeries(axiz);
        mDataset.addSeries(mCurSer);
        mCurRen = new XYSeriesRenderer();
        mRenderer.addSeriesRenderer(mCurRen);
        setCharts(axiz);
        mChart = ChartFactory.getCubeLineChartView(context,mDataset,mRenderer,0.3f);
    }

    public void updateChart(float time,float X){
        Float[] XY = new Float[2];
        XY[0] = time;
        XY[1] = X;
        addXY.add(XY);
        mCurSer.clear();
        for (int i = 0; i <addXY.size(); i++){
            mCurSer.add(time - addXY.get(i)[0],addXY.get(i)[1]);
        }
        mChart.invalidate();
    }

    private void setCharts(String axis){
        mRenderer.setChartTitle(axis);
        mRenderer.setXTitle("Time(s)");
        mRenderer.setXAxisMax(100);
        mRenderer.setXAxisMin(0);
        mRenderer.setXLabels(21);
        mRenderer.setYTitle("Acceleration(m/s)");
        mRenderer.setYAxisMax(20);
        mRenderer.setYAxisMin(-20);
        mRenderer.setYLabels(9);
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);

        mRenderer.setShowGrid(true);
        mRenderer.setGridColor(Color.GREEN);
    }
    public  void cleanOut(){
        mCurSer.clear();
        addXY.clear();
    }
}
