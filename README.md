siddhi-execution-math
======================================

The **siddhi-execution-math extension** is an extension to <a target="_blank" href="https://wso2.github.io/siddhi">Siddhi</a> that contains useful mathematical functions to make your siddhi queries more flexible.

Find some useful links below:

* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-math">Source code</a>
* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-math/releases">Releases</a>
* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-math/issues">Issue tracker</a>

## Latest API Docs 

Latest API Docs is <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12">4.0.12</a>.

## How to use 

**Using the extension in <a target="_blank" href="https://github.com/wso2/product-sp">WSO2 Stream Processor</a>**

* You can use this extension in the latest <a target="_blank" href="https://github.com/wso2/product-sp/releases">WSO2 Stream Processor</a> that is a part of <a target="_blank" href="http://wso2.com/analytics?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">WSO2 Analytics</a> offering, with editor, debugger and simulation support. 

* This extension is shipped by default with WSO2 Stream Processor, if you wish to use an alternative version of this extension you can replace the component <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-math/releases">jar</a> that can be found in the `<STREAM_PROCESSOR_HOME>/lib` directory.

**Using the extension as a <a target="_blank" href="https://wso2.github.io/siddhi/documentation/running-as-a-java-library">java library</a>**

* This extension can be added as a maven dependency along with other Siddhi dependencies to your project.

```
     <dependency>
        <groupId>org.wso2.extension.siddhi.execution.math</groupId>
        <artifactId>siddhi-execution-math</artifactId>
        <version>x.x.x</version>
     </dependency>
```

## Jenkins Build Status

---

|  Branch | Build Status |
| :------ |:------------ | 
| master  | [![Build Status](https://wso2.org/jenkins/job/siddhi/job/siddhi-execution-math/badge/icon)](https://wso2.org/jenkins/job/siddhi/job/siddhi-execution-math/) |

---

## Features

* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#percentile-aggregate-function">percentile</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#aggregate-function">(Aggregate Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the pth percentile value of the arg values.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#abs-function">abs</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the absolute value of first parameter. This function wraps the java.lang.Math.abs() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#acos-function">acos</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>If -1 &lt;= p1 &lt;= 1, this function returns the arc-cosine (inverse cosine) of p1. If not, it returns NULL. The return value is in radian scale. This function wraps the java.lang.Math.acos()function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#asin-function">asin</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>If -1 &lt;= p1 &lt;= 1, this function returns the arc-sin (inverse sine) of p1. If not, it returns NULL. The return value is in radian scale. This function wraps the java.lang.Math.asin() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#atan-function">atan</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>1. math:atan(p1) Returns the arc-tangent (inverse tangent) of p1. The return value is in radian scale. This function wraps the java.lang.Math.atan() function.<br>2. Returns the arc-tangent (inverse tangent) of  p1 and p2 coordinates. The return value is in radian scale. This function wraps the java.lang.Math.atan2() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#bin-function">bin</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns a string representation of the integer/long p1 argument as an unsigned integer in base 2. This function wraps the java.lang.Integer.toBinaryString and java.lang.Long.toBinaryString methods.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#cbrt-function">cbrt</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the cube-root of p1 (p1 is in radians). This function wraps the java.lang.Math.cbrt() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#ceil-function">ceil</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the smallest (closest to negative infinity) double value that is greater than or equal to the p1 argument, and is equal to a mathematical integer. This function wraps thejava.lang.Math.ceil() method.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#conv-function">conv</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Converts a from the fromBase base to the toBase base.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#copysign-function">copySign</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the magnitude of magnitude with the sign of sign . This function wraps the java.lang.Math.copySign() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#cos-function">cos</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the cosine of p1 (p1 is in radians). This function wraps the java.lang.Math.cos() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#cosh-function">cosh</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the hyperbolic cosine of p1 (p1 is in radians). This function wraps the java.lang.Math.cosh() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#e-function">e</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the java.lang.Math.E constant, which is the closest double value to e (which is the base of the natural logarithms).</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#exp-function">exp</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns Euler's number e raised to the power of p1. This function wraps the java.lang.Math.exp() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#floor-function">floor</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>This function wraps the java.lang.Math.floor() function that returns the largest (closest to positive infinity) value that is less that or equal to p1, and is equal to a mathematical integer.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#getexponent-function">getExponent</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the unbiased exponent used in the representation of p1. This function wraps the java.lang.Math.getExponent() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#hex-function">hex</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>This function wraps the java.lang.Double.toHexString() function that returns a hexadecimal string representation of p1.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#isinfinite-function">isInfinite</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>This function wraps the java.lang.Float.isInfinite() and java.lang.Double.isInfinite() functions that return true if p1 is infinitely large in magnitude, or return false otherwise.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#isnan-function">isNan</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>This function wraps the java.lang.Float.isNaN() and java.lang.Double.isNaN() functions that return true if p1 is a NaN (Not-a-Number) value, or return false otherwise.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#ln-function">ln</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the natural logarithm (base e) of p1.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#log-function">log</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the logarithm (base=base) of number.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#log10-function">log10</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the base 10 logarithm of p1.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#log2-function">log2</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the base 2 logarithm of p1.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#max-function">max</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the greater value out of p1 and p2.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#min-function">min</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the smaller value out of p1 and p2.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#oct-function">oct</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Converts p1 to octal.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#parsedouble-function">parseDouble</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns str as a double.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#parsefloat-function">parseFloat</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns str as a float.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#parseint-function">parseInt</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns str as a int.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#parselong-function">parseLong</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns str as a long.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#pi-function">pi</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the java.lang.Math.PI constant, which is the closest value to pi (i.e. the ratio of the circumference of a circle to its diameter). </p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#power-function">power</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns value raised to the power of toPower.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#rand-function">rand</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>1.  A sequence of calls to rand() generates a stream of pseudo-random numbers. This function uses the java.util.Random class internally.<br>2. A sequence of calls to rand(seed) generates a stream of pseudo-random numbers. This function uses the java.util.Random class internally. </p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#round-function">round</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the closest integer/long (depending on the input) value to the argument.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#signum-function">signum</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>If a is a positive, this returns the sign of p1 as 1.0. This function wraps the java.lang.Math.signum() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#sin-function">sin</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the sine of p1 (p1 is in radians). This function wraps the java.lang.Math.sin() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#sinh-function">sinh</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the hyperbolic sine of p1 (p1 is in radians). This function wraps the java.lang.Math.sinh() function. </p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#sqrt-function">sqrt</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the square-root of p1. This function wraps the java.lang.Math.sqrt() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#tan-function">tan</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the tan of p1 (p1 is in radians). This function wraps the java.lang.Math.tan() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#tanh-function">tanh</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Returns the hyperbolic tangent of p1 (p1 is in radians). This function wraps the java.lang.Math.tanh() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#todegrees-function">toDegrees</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Converts p1 from radians to degrees. This function wraps the java.lang.Math.toDegrees() function.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-math/api/4.0.12/#toradians-function">toRadians</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>)*<br><div style="padding-left: 1em;"><p>Converts p1 from degrees to radians. This function wraps the java.lang.Math.toRadians() function.</p></div>

## How to Contribute
 
  * Please report issues at <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-math/issues">GitHub Issue Tracker</a>.
  
  * Send your contributions as pull requests to <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-math/tree/master">master branch</a>. 
 
## Contact us 

 * Post your questions with the <a target="_blank" href="http://stackoverflow.com/search?q=siddhi">"Siddhi"</a> tag in <a target="_blank" href="http://stackoverflow.com/search?q=siddhi">Stackoverflow</a>. 
 
 * Siddhi developers can be contacted via the mailing lists:
 
    Developers List   : [dev@wso2.org](mailto:dev@wso2.org)
    
    Architecture List : [architecture@wso2.org](mailto:architecture@wso2.org)
 
## Support 

* We are committed to ensuring support for this extension in production. Our unique approach ensures that all support leverages our open development methodology and is provided by the very same engineers who build the technology. 

* For more details and to take advantage of this unique opportunity contact us via <a target="_blank" href="http://wso2.com/support?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">http://wso2.com/support/</a>. 
