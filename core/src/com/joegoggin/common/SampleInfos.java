package com.joegoggin.common;

import com.joegoggin.ApplicationListenerSample;
import com.joegoggin.GdxGeneratedSample;
import com.joegoggin.GdxModuleInfoSample;
import com.joegoggin.GdxReflectionSample;
import com.joegoggin.InputListeningSample;
import com.joegoggin.InputPollingSample;
import com.joegoggin.OrthographicCameraSample;
import com.joegoggin.SpriteBatchSample;
import com.joegoggin.ViewportSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleInfos {

    public static final List<SampleInfo> ALL = Arrays.asList(
            ApplicationListenerSample.SAMPLE_INFO,
            GdxGeneratedSample.SAMPLE_INFO,
            GdxModuleInfoSample.SAMPLE_INFO,
            GdxReflectionSample.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO,
            OrthographicCameraSample.SAMPLE_INFO,
            ViewportSample.SAMPLE_INFO,
            SpriteBatchSample.SAMPLE_INFO

    );

    public static List<String> getSampleNames() {
        List<String> ret = new ArrayList<String>();

        for (SampleInfo info : ALL) {
            ret.add(info.getName());
        }

        Collections.sort(ret);
        return ret;
    }

    public static SampleInfo find(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name argument is required.");
        }

        SampleInfo ret = null;

        for (SampleInfo info : ALL) {
            if (info.getName().equals(name)) {
                ret = info;
                break;
            }
        }

        if (ret == null) {
            throw new IllegalArgumentException("Couldn't find sample with name = " + name);
        }

        return ret;
    }

    private SampleInfos() {

    }
}
