package com.astuetz.viewpager.extensions.sample.fragment;

import android.animation.Animator;
import android.animation.IntEvaluator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.astuetz.viewpager.extensions.sample.R;

public class PropertyAnimatorFragment extends Fragment implements View.OnClickListener {
    private TextView tvTest1, tvTest2;
    ValueAnimator animator1;
    ValueAnimator animator2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_property_nimator, container, false);
        initView(rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        tvTest1 = view.findViewById(R.id.tv_test1);
        tvTest1.setOnClickListener(this);

    }

    private void doAnimatorTest1() {
        animator1 = ValueAnimator.ofInt(0, 400);
        animator1.setDuration(1000);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int curValue = (int) animator1.getAnimatedValue();
                tvTest1.layout(curValue, curValue, curValue + tvTest1.getWidth(), curValue + tvTest1.getHeight());
            }
        });
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator1.setInterpolator(new BounceInterpolator());
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setEvaluator(new IntEvaluator());
        animator1.start();

    }

    private void doAnimatorTest2() {
        animator2 = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (char) animation.getAnimatedValue();
                tvTest2.setText(String.valueOf(text));
            }
        });
        animator2.setDuration(10000);
        animator2.setInterpolator(new AccelerateInterpolator());
        animator2.start();
    }


    private void stopAnimator1() {
        if (animator1.isRunning()) {
            animator1.cancel();
        }
    }

    private void stopAnimator2() {
        if (animator2.isRunning()) {
            animator2.cancel();
        }
    }


    //定义插值器，只需要实现TimeInterpolator接口
    class MyInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            return 1 - input;
        }
    }

    class MyEvaluator implements TypeEvaluator<Integer> {
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            int startInt = startValue;
            return (int) (200 + startInt + fraction * (endValue - startInt));

        }
    }

    class ReverseEvaluator implements TypeEvaluator<Integer> {
        //float 其实就是getInterpolation的返回值
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            int startInt = startValue;
            return (int) (endValue - fraction * (endValue - startInt));
        }
    }


    class CharEvaluator implements TypeEvaluator<Character> {
        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = (int) startValue;
            int endInt = (int) endValue;
            int curInt = (int) (startInt + fraction * (endInt - startInt));
            char result = (char) curInt;
            return result;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_test1:
                doAnimatorTest1();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
