package com.fichardu.interpolator;

import android.animation.TimeInterpolator;

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 fichardu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class EaseOutBounceInterpolator implements TimeInterpolator{

    private static final float s = 1.70158f;

    @Override
    public float getInterpolation(float input) {
        if (input < 1/2.75f) {
            return 7.5625f*input*input;
        } else if (input < 2/2.75f) {
            input -= 1.5f/2.75f;
            return 7.5625f*input*input + 0.75f;
        } else if (input < 2.5f/2.75f) {
            input -= 2.25f/2.75f;
            return 7.5625f*input*input - 0.9375f;
        } else {
            input -= 2.625f/2.75f;
            return 7.5625f*input*input - 0.984375f;
        }
    }

}
