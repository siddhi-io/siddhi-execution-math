# API Docs - v4.0.9

## Math

### percentile *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#aggregate-function">(Aggregate Function)</a>*

<p style="word-wrap: break-word">Returns the pth percentile value of the arg values.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:percentile(<INT|LONG|FLOAT|DOUBLE> arg, <DOUBLE> p)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The values of which the percentile should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">p</td>
        <td style="vertical-align: top; word-wrap: break-word">Estimate of which percentile to be found (pth percentile) where p is any number greater than 0 or less than or equal to 100</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (sensorId int, temperature double); 
from InValueStream 
select math:percentile(temperature, 97.0) as percentile 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">math:percentile(temperature, 97.0)</p>

### abs *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the absolute value of first parameter. This function wraps the java.lang.Math.abs() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:abs(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of which the absolute value should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:abs(inValue) as absValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">Both abs(3) and abs(-3) queries return 3 since the absolute value of both 3 and -3 is 3.</p>

### acos *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">If -1 &lt;= p1 &lt;= 1, this function returns the arc-cosine (inverse cosine) of p1. If not, it returns NULL. The return value is in radian scale. This function wraps the java.lang.Math.acos()function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:acos(<FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of which the arc-cosine (inverse cosine) should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:acos(inValue) as acosValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">acos(0.5) returns 1.0471975511965979.</p>

### asin *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">If -1 &lt;= p1 &lt;= 1, this function returns the arc-sin (inverse sine) of p1. If not, it returns NULL. The return value is in radian scale. This function wraps the java.lang.Math.asin() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:asin(<FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of which the arc-sin (inverse sine) should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:asin(inValue) as asinValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">asin(0.5) returns 0.5235987755982989.</p>

### atan *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">1. math:atan(p1) Returns the arc-tangent (inverse tangent) of p1. The return value is in radian scale. This function wraps the java.lang.Math.atan() function.<br>2. Returns the arc-tangent (inverse tangent) of  p1 and p2 coordinates. The return value is in radian scale. This function wraps the java.lang.Math.atan2() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:atan(<INT|LONG|FLOAT|DOUBLE> p1, <INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of which the arc-tangent (inverse tangent) should be found. If the optional second parameter is given this represents the x coordinate of the (x,y) coordinate pair</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">This optional parameter represents the y coordinate of the (x,y) coordinate pair</td>
        <td style="vertical-align: top">0D</td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue1 double, inValue2 double); 
from InValueStream 
select math:atan(inValue1, inValue2) as convertedValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">atan(12d, 5d) returns 1.1760052070951352.</p>

### bin *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns a string representation of the integer/long p1 argument as an unsigned integer in base 2. This function wraps the java.lang.Integer.toBinaryString and java.lang.Long.toBinaryString methods.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<STRING> math:bin(<INT|LONG> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be converted to an unsigned integer of base 2</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue long); 
from InValueStream 
select math:bin(inValue) as binValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">bin(9) returns "1001".</p>

### cbrt *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the cube-root of p1 (p1 is in radians). This function wraps the java.lang.Math.cbrt() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:cbrt(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose cube-root should be found. Input must be in radians</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:cbrt(inValue) as cbrtValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">cbrt(17d) returns 2.5712815906582356.</p>

### ceil *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the smallest (closest to negative infinity) double value that is greater than or equal to the p1 argument, and is equal to a mathematical integer. This function wraps thejava.lang.Math.ceil() method.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:ceil(<FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose ceiling value should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:ceil(inValue) as ceilingValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">ceil(423.187d) returns 424.0.</p>

### conv *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Converts a from the fromBase base to the toBase base.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<STRING> math:conv(<STRING> a, <INT> from.base, <INT> to.base)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">a</td>
        <td style="vertical-align: top; word-wrap: break-word">The value whose base should be changed. Input should be as a String</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">from.base</td>
        <td style="vertical-align: top; word-wrap: break-word">The source base of the input parameter 'a'</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">to.base</td>
        <td style="vertical-align: top; word-wrap: break-word">The target base that input parameter 'a' should be converted to</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue string,fromBase int,toBase int); 
from InValueStream 
select math:conv(inValue,fromBase,toBase) as convertedValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">conv("7f", 16, 10) returns "127".</p>

### copySign *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the magnitude of magnitude with the sign of sign . This function wraps the java.lang.Math.copySign() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:copySign(<INT|LONG|FLOAT|DOUBLE> magnitude, <INT|LONG|FLOAT|DOUBLE> sign)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">magnitude</td>
        <td style="vertical-align: top; word-wrap: break-word">This parameters magnitude will be used for output attribute</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">sign</td>
        <td style="vertical-align: top; word-wrap: break-word">This parameters sign will be used for output attribute</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue1 double, inValue2 double); 
from InValueStream 
select math:copySign(inValue1,inValue2) as copysignValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">copySign(5.6d, -3.0d) returns -5.6.</p>

### cos *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the cosine of p1 (p1 is in radians). This function wraps the java.lang.Math.cos() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:cos(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose cosine value should be found. Input must be in radians</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:cos(inValue) as cosValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">cos(6d) returns 0.9601702866503661.</p>

### cosh *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the hyperbolic cosine of p1 (p1 is in radians). This function wraps the java.lang.Math.cosh() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:cosh(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose hyperbolic cosine should be found. Input must be in radians</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:cosh(inValue) as cosValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">cosh (6d) returns 201.7156361224559.</p>

### e *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the java.lang.Math.E constant, which is the closest double value to e (which is the base of the natural logarithms).</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:e()
```

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:e() as eValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">e() returns 2.7182818284590452354.</p>

### exp *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns Euler's number e raised to the power of p1. This function wraps the java.lang.Math.exp() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:exp(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The power that the Euler's number e should be raised to</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:exp(inValue) as expValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">exp(10.23) returns 27722.51006805505.</p>

### floor *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">This function wraps the java.lang.Math.floor() function that returns the largest (closest to positive infinity) value that is less that or equal to p1, and is equal to a mathematical integer.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:floor(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose floor value should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:floor(inValue) as floorValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">floor(10.23) returns 10.0.</p>

### getExponent *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the unbiased exponent used in the representation of p1. This function wraps the java.lang.Math.getExponent() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<INT> math:getExponent(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose unbiased exponent representation should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:getExponent(inValue) as expValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">getExponent(60984.1) returns 15.</p>

### hex *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">This function wraps the java.lang.Double.toHexString() function that returns a hexadecimal string representation of p1.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<STRING> math:hex(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose hexadecimal representation should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue int); 
from InValueStream 
select math:hex(inValue) as hexString 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">hex(200) returns "c8".</p>

### isInfinite *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">This function wraps the java.lang.Float.isInfinite() and java.lang.Double.isInfinite() functions that return true if p1 is infinitely large in magnitude, or return false otherwise.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<BOOL> math:isInfinite(<FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be checked if infinite</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue1 double,inValue2 int); 
from InValueStream 
select math:isInfinite(inValue1) as isInfinite 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">isInfinite(java.lang.Double.POSITIVE_INFINITY) returns true.</p>

### isNan *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">This function wraps the java.lang.Float.isNaN() and java.lang.Double.isNaN() functions that return true if p1 is a NaN (Not-a-Number) value, or return false otherwise.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<BOOL> math:isNan(<FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be checked if it is a number</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue1 double,inValue2 int); 
from InValueStream 
select math:isNan(inValue1) as isNaN 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">isNan(java.lang.Math.log(-12d)) returns true.</p>

### ln *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the natural logarithm (base e) of p1.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:ln(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose natural logarithm (base e) should be found</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:ln(inValue) as lnValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">ln(11.453) returns 2.438251704415579.</p>

### log *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the logarithm (base=base) of number.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:log(<INT|LONG|FLOAT|DOUBLE> number, <INT|LONG|FLOAT|DOUBLE> base)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">number</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose base should be changed</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">base</td>
        <td style="vertical-align: top; word-wrap: break-word">The base value of the ouput</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (number double, base double); 
from InValueStream 
select math:log(number, base) as logValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">log(34, 2f) returns 5.08746284125034.</p>

### log10 *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the base 10 logarithm of p1.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:log10(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose base 10 logarithm should be found.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:log10(inValue) as lnValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">log10(19.234) returns 1.2840696117100832.</p>

### log2 *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the base 2 logarithm of p1.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:log2(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose base 2 logarithm should be found.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:log2(inValue) as lnValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">log2(91d) returns 6.507794640198696.</p>

### max *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the greater value out of p1 and p2.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:max(<INT|LONG|FLOAT|DOUBLE> p1, <INT|LONG|FLOAT|DOUBLE> p2)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">Value one to be compared in finding largest value</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">p2</td>
        <td style="vertical-align: top; word-wrap: break-word">Value two to be compared in finding largest value</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue1 double,inValue2 int); 
from InValueStream 
select math:max(inValue1,inValue2) as maxValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">max(123.67d, 91) returns 123.67.</p>

### min *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the smaller value out of p1 and p2.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:min(<INT|LONG|FLOAT|DOUBLE> p1, <INT|LONG|FLOAT|DOUBLE> p2)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">Value one to be compared in finding smallest value</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">p2</td>
        <td style="vertical-align: top; word-wrap: break-word">Value two to be compared in finding smallest value</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue1 double,inValue2 int); 
from InValueStream 
select math:min(inValue1,inValue2) as minValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">min(123.67d, 91) returns 91.</p>

### oct *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Converts p1 to octal.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<STRING> math:oct(<INT|LONG> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose octal representation should be found.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue long); 
from InValueStream 
select math:oct(inValue) as octValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">oct(99l) returns "143".</p>

### parseDouble *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns str as a double.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:parseDouble(<STRING> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be converted to a double</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue string); 
from InValueStream 
select math:parseDouble(inValue) as output 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">parseDouble("123") returns 123.0.</p>

### parseFloat *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns str as a float.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<FLOAT> math:parseFloat(<STRING> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be converted to a float</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue string); 
from InValueStream 
select math:parseFloat(inValue) as output 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">parseFloat("123") returns 123.0.</p>

### parseInt *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns str as a int.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<INT> math:parseInt(<STRING> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be converted to a int</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue string); 
from InValueStream 
select math:parseInt(inValue) as output 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">parseInt("123") returns 123.</p>

### parseLong *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns str as a long.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<LONG> math:parseLong(<STRING> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be converted to a long</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue string); 
from InValueStream 
select math:parseLong(inValue) as output 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">parseLong("123") returns 123.</p>

### pi *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the java.lang.Math.PI constant, which is the closest value to pi (i.e. the ratio of the circumference of a circle to its diameter). </p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:pi()
```

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:pi() as piValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">pi() always returns 3.141592653589793.</p>

### power *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns value raised to the power of toPower.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:power(<INT|LONG|FLOAT|DOUBLE> value, <INT|LONG|FLOAT|DOUBLE> to.power)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be raised to the power of 'to.power' input parameter</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">to.power</td>
        <td style="vertical-align: top; word-wrap: break-word">The power that 'value' input parameter should be raised to</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue1 double, inValue2 double); 
from InValueStream 
select math:power(inValue1,inValue2) as powerValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">power(5.6d, 3.0d) returns 175.61599999999996.</p>

### rand *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">1.  A sequence of calls to rand() generates a stream of pseudo-random numbers. This function uses the java.util.Random class internally.<br>2. A sequence of calls to rand(seed) generates a stream of pseudo-random numbers. This function uses the java.util.Random class internally. </p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:rand(<INT|LONG> seed)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">seed</td>
        <td style="vertical-align: top; word-wrap: break-word">An optional seed value that will be used to generate the random number sequence</td>
        <td style="vertical-align: top">defaultSeed</td>
        <td style="vertical-align: top">INT<br>LONG</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (symbol string, price long, volume long); 
from InValueStream select symbol, math:rand() as randNumber 
select math:oct(inValue) as octValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">A random double value between 0 and 1 will be generated using math:rand()</p>

### round *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the closest integer/long (depending on the input) value to the argument.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<INT|LONG> math:round(<FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be rounded to the closest integer/long.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:round(inValue) as roundValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">round(3252.353) returns 3252.</p>

### signum *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">If a is a positive, this returns the sign of p1 as 1.0. This function wraps the java.lang.Math.signum() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<INT> math:signum(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be checked if positive or negative or otherwise</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:signum(inValue) as sign 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">signum(-6.32d) returns -1.</p>

### sin *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the sine of p1 (p1 is in radians). This function wraps the java.lang.Math.sin() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:sin(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose sine value should be found. Input should be in radians</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:sin(inValue) as sinValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">sin(6d) returns -0.27941549819892586.</p>

### sinh *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the hyperbolic sine of p1 (p1 is in radians). This function wraps the java.lang.Math.sinh() function. </p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:sinh(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose hyperbolic sine value should be found. Input should be in radians</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:sinh(inValue) as sinhValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">sinh(6d) returns 201.71315737027922.</p>

### sqrt *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the square-root of p1. This function wraps the java.lang.Math.sqrt() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:sqrt(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose square-root value should be found.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:sqrt(inValue) as sqrtValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">sqrt(4d) returns 2.</p>

### tan *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the tan of p1 (p1 is in radians). This function wraps the java.lang.Math.tan() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:tan(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose tan value should be found. Input should be in radians</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:tan(inValue) as tanValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">tan(6d) returns -0.29100619138474915.</p>

### tanh *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Returns the hyperbolic tangent of p1 (p1 is in radians). This function wraps the java.lang.Math.tanh() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:tanh(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The value of whose hyperbolic tangent value should be found. Input should be in radians</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:tanh(inValue) as tanhValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">tanh(6d) returns 0.9999877116507956.</p>

### toDegrees *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Converts p1 from radians to degrees. This function wraps the java.lang.Math.toDegrees() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:toDegrees(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The radians value that should be converted to degrees</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:toDegrees(inValue) as degreesValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">toDegrees(6d) returns 343.77467707849394.</p>

### toRadians *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*

<p style="word-wrap: break-word">Converts p1 from degrees to radians. This function wraps the java.lang.Math.toRadians() function.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
<DOUBLE> math:toRadians(<INT|LONG|FLOAT|DOUBLE> p1)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">p1</td>
        <td style="vertical-align: top; word-wrap: break-word">The degrees value that should be converted to radians</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InValueStream (inValue double); 
from InValueStream 
select math:toRadians(inValue) as radiansValue 
insert into OutMediationStream;
```
<p style="word-wrap: break-word">toRadians(6d) returns 0.10471975511965977.</p>

