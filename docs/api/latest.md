# API Docs - v5.0.6

!!! Info "Tested Siddhi Core version: *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/">5.1.21</a>*"
    It could also support other Siddhi Core minor versions.

## Math

### percentile *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This functions returns the pth percentile value of a given argument.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose percentile should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">p</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Estimate of the percentile to be found (pth percentile) where p is any number greater than 0 or lesser than or equal to 100.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the percentile value based on the argument given. For example, math:percentile(temperature, 97.0) returns the 97th percentile value of all the temperature events.</p>
<p></p>
### abs *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the absolute value of the given parameter. It wraps the <code>java.lang.Math.abs()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The parameter whose absolute value is found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">Irrespective of whether the 'invalue' in the input stream holds a value of abs(3) or abs(-3),the function returns 3 since the absolute value of both 3 and -3 is 3. The result directed to OutMediationStream stream.</p>
<p></p>
### acos *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">If -1 &lt;= p1 &lt;= 1, this function returns the arc-cosine (inverse cosine) value of p1.If the domain is invalid, it returns NULL. The value returned is in radian scale. This function wraps the java.lang.Math.acos() function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose arc-cosine (inverse cosine) value is found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is given, the function calculates the arc-cosine value of it and returns the arc-cosine value to the output stream, OutMediationStream. For example, acos(0.5) returns 1.0471975511965979.</p>
<p></p>
### asin *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">If -1 &lt;= p1 &lt;= 1, this function returns the arc-sin (inverse sine) value of p1. If the domain is invalid, it returns NULL. The value returned is in radian scale. This function wraps the java.lang.Math.asin() function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose arc-sin (inverse sine) value is found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is given, the function calculates the arc-sin value of it and returns the arc-sin value to the output stream, OutMediationStream. For example, asin(0.5) returns 0.5235987755982989.</p>
<p></p>
### atan *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">1. If a single <code>p1</code> is received, this function returns the arc-tangent (inverse tangent) value of <code>p1</code>. <br>2. If <code>p1</code> is received along with an optional <code>p1</code>, it considers them as x and y coordinates and returns the arc-tangent (inverse tangent) value. <br>The returned value is in radian scale. This function wraps the <code>java.lang.Math.atan()</code> function.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<DOUBLE> math:atan(<INT|LONG|FLOAT|DOUBLE> p1)
<DOUBLE> math:atan(<INT|LONG|FLOAT|DOUBLE> p1, <INT|LONG|FLOAT|DOUBLE> p2)
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose arc-tangent (inverse tangent) is found. If the optional second parameter is given this represents the x coordinate of the (x,y) coordinate pair.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">p2</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">This optional parameter represents the y coordinate of the (x,y) coordinate pair.</p></td>
        <td style="vertical-align: top">0D</td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue1' in the input stream is given, the function calculates the arc-tangent value of it and returns the arc-tangent value to the output stream, OutMediationStream. If both the 'inValue1' and 'inValue2' are given, then the function considers them to be x and y coordinates respectively and returns the calculated arc-tangent value to the output stream, OutMediationStream. For example, atan(12d, 5d) returns 1.1760052070951352.</p>
<p></p>
### bin *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns a string representation of the p1 argument, that is of either 'integer' or 'long' data type, as an unsigned integer in base 2. It wraps the <code>java.lang.Integer.toBinaryString</code> and java.lang.Long.toBinaryString` methods.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value in either 'integer' or 'long', that should be converted into an unsigned integer of base 2.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is given, the function converts it into an unsigned integer in base 2 and directs the output to the output stream, OutMediationStream. For example, bin(9) returns '1001'.</p>
<p></p>
### cbrt *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the cube-root of 'p1' which is in radians. It wraps the <code>java.lang.Math.cbrt()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose cube-root should be found. Input is required to be in radians.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' is given, the function calculates the cube-root value for the same and directs the output to the output stream, OutMediationStream. For example, cbrt(17d) returns 2.5712815906582356.</p>
<p></p>
### ceil *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the smallest double value, i.e., the closest to the negative infinity, that is greater than or equal to the <code>p1</code> argument, and is equal to a mathematical integer. It wraps the <code>java.lang.Math.ceil()</code> method.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose ceiling value is found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function calculates the ceiling value of the given 'inValue' and directs the result to 'OutMediationStream' output stream. For example, ceil(423.187d) returns 424.0.</p>
<p></p>
### conv *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function converts <code>a</code> from the <code>fromBase</code> base to the <code>toBase</code> base.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value whose base should be changed. Input should be given as a 'String'.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">from.base</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The source base of the input parameter 'a'.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">to.base</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The target base that the input parameter 'a' should be converted into.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is given, and the base in which it currently resides in and the base to which it should be converted to is specified, the function converts it into a string in the target base and directs it to the output stream, OutMediationStream. For example, conv("7f", 16, 10) returns "127".</p>
<p></p>
### copySign *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns a value of an input with the received <code>magnitude</code> and <code>sign</code> of another input. It wraps the <code>java.lang.Math.copySign()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The magnitude of this parameter is used in the output attribute.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">sign</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The sign of this parameter is used in the output attribute.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If two values are provided as 'inValue1' and 'inValue2', the function copies the magnitude and sign of the second argument into the first one and directs the result to the output stream, OutMediatonStream. For example, copySign(5.6d, -3.0d) returns -5.6.</p>
<p></p>
### cos *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the cosine of <code>p1</code> which is in radians. It wraps the <code>java.lang.Math.cos()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose cosine value should be found.The input is required to be in radians.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' is given, the function calculates the cosine value for the same and directs the output to the output stream, OutMediationStream. For example, cos(6d) returns 0.9601702866503661.</p>
<p></p>
### cosh *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the hyperbolic cosine of <code>p1</code> which is in radians. It wraps the <code>java.lang.Math.cosh()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose hyperbolic cosine should be found. The input is required to be in radians.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' is given, the function calculates the hyperbolic cosine value for the same and directs the output to the output stream, OutMediationStream. For example, cosh (6d) returns 201.7156361224559.</p>
<p></p>
### e *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the <code>java.lang.Math.E</code> constant, which is the closest double value to e, where e is the base of the natural logarithms. </p>
<p></p>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the constant, 2.7182818284590452354 which is the closest double value to e and directs the output to 'OutMediationStream' output stream.</p>
<p></p>
### exp *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the Euler's number <code>e</code> raised to the power of <code>p1</code>. It wraps the <code>java.lang.Math.exp()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The power that the Euler's number e is raised to.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the inputstream holds a value, this function calculates the corresponding Euler's number 'e' and directs it to the output stream, OutMediationStream. For example, exp(10.23) returns 27722.51006805505.</p>
<p></p>
### floor *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function wraps the <code>java.lang.Math.floor()</code> function and returns the largest value, i.e., closest to the positive infinity, that is less than or equal to <code>p1</code>, and is equal to a mathematical integer.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose floor value should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function calculates the floor value of the given 'inValue' input and directs the output to the 'OutMediationStream' output stream. For example, (10.23) returns 10.0.</p>
<p></p>
### getExponent *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the unbiased exponent that is used in the representation of <code>p1</code>. This function wraps the <code>java.lang.Math.getExponent()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of whose unbiased exponent representation should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function calculates the unbiased exponent of a given input, 'inValue' and directs the result to the 'OutMediationStream' output stream. For example, getExponent(60984.1) returns 15.</p>
<p></p>
### hex *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function wraps the <code>java.lang.Double.toHexString() function. It returns a hexadecimal string representation of the input, </code>p1`.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose hexadecimal value should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is provided, the function converts this into its corresponding hexadecimal format and directs the output to the output stream, OutMediationStream. For example, hex(200) returns "c8".</p>
<p></p>
### isInfinite *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function wraps the <code>java.lang.Float.isInfinite()</code> and <code>java.lang.Double.isInfinite()</code> and returns <code>true</code> if <code>p1</code> is infinitely large in magnitude and <code>false</code> if otherwise.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">This is the value of the parameter that the function determines to be either infinite or finite.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the value given in the 'inValue' in the input stream is of infinitely large magnitude, the function returns the value, 'true' and directs the result to the output stream, OutMediationStream'. For example, isInfinite(java.lang.Double.POSITIVE_INFINITY) returns true.</p>
<p></p>
### isNan *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function wraps the <code>java.lang.Float.isNaN()</code> and <code>java.lang.Double.isNaN()</code> functions and returns <code>true</code> if <code>p1</code> is NaN (Not-a-Number), and returns <code>false</code> if otherwise.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter which the function determines to be either NaN or a number.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue1' in the input stream has a value that is undefined, then the function considers it as an 'NaN' value and directs 'True' to the output stream, OutMediationStream. For example, isNan(java.lang.Math.log(-12d)) returns true.</p>
<p></p>
### ln *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the natural logarithm (base e) of <code>p1</code>.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose natural logarithm (base e) should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is given, the function calculates its natural logarithm (base e) and directs the results to the output stream, 'OutMeditionStream'. For example, ln(11.453) returns 2.438251704415579.</p>
<p></p>
### log *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the logarithm of the received <code>number</code> as per the given <code>base</code>.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose base should be changed.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">base</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The base value of the ouput.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the number and the base to which it has to be converted into is given in the input stream, the function calculates the number to the base specified and directs the result to the output stream, OutMediationStream. For example, log(34, 2f) returns 5.08746284125034.</p>
<p></p>
### log10 *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the base 10 logarithm of <code>p1</code>.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose base 10 logarithm should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is given, the function calculates the base 10 logarithm of the same and directs the result to the output stream, OutMediatioStream. For example, log10(19.234) returns 1.2840696117100832.</p>
<p></p>
### log2 *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the base 2 logarithm of <code>p1</code>.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose base 2 logarithm should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is given, the function calculates the base 2 logarithm of the same and returns the value to the output stream, OutMediationStream. For example log2(91d) returns 6.507794640198696.</p>
<p></p>
### max *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the greater value of <code>p1</code> and <code>p2</code>.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">One of the input values to be compared in order to find the larger value of the two</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">p2</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The input value to be compared with 'p1' in order to find the larger value of the two.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If two input values 'inValue1, and 'inValue2' are given, the function compares them and directs the larger value to the output stream, OutMediationStream. For example, max(123.67d, 91) returns 123.67.</p>
<p></p>
### min *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the smaller value of <code>p1</code> and <code>p2</code>.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">One of the input values that are to be compared in order to find the smaller value.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">p2</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The input value that is to be compared with 'p1' in order to find the smaller value.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If two input values, 'inValue1' and 'inValue2' are given, the function compares them and directs the smaller value of the two to the output stream, OutMediationStream. For example, min(123.67d, 91) returns 91.</p>
<p></p>
### oct *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function converts the input parameter <code>p1</code> to octal.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose octal representation should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream is given, this function calculates the octal value corresponding to the same and directs it to the output stream, OutMediationStream. For example, oct(99l) returns "143".</p>
<p></p>
### parseDouble *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the double value of the string received.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value that should be converted into a double value.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inValue' in the input stream holds a value, this function converts it into the corresponding double value and directs it to the output stream, OutMediationStream. For example, parseDouble("123") returns 123.0.</p>
<p></p>
### parseFloat *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the float value of the received string.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value that should be converted into a float value.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">The function converts the input value given in 'inValue',into its corresponding float value and directs the result into the output stream, OutMediationStream. For example, parseFloat("123") returns 123.0.</p>
<p></p>
### parseInt *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the integer value of the received string.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value that should be converted to an integer.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">The  function converts the 'inValue' into its corresponding integer value and directs the output to the output stream, OutMediationStream. For example, parseInt("123") returns 123.</p>
<p></p>
### parseLong *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the long value of the string received.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value that should be converted to a long value.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">The function converts the 'inValue' to its corresponding long value and directs the result to the output stream, OutMediationStream. For example, parseLong("123") returns 123.</p>
<p></p>
### pi *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the <code>java.lang.Math.PI</code> constant, which is the closest value to pi, i.e., the ratio of the circumference of a circle to its diameter. </p>
<p></p>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">pi() always returns 3.141592653589793.</p>
<p></p>
### power *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function raises the given value to a given power.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value that should be raised to the power of 'to.power' input parameter.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">to.power</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The power to which the 'value' input parameter should be raised.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function raises the 'inValue1' to the power of 'inValue2' and directs the output to the output stream, 'OutMediationStream. For example, (5.6d, 3.0d) returns 175.61599999999996.</p>
<p></p>
### rand *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns a stream of pseudo-random numbers when a sequence of calls are sent to the <code>rand()</code>. Optionally, it is possible to define a seed, i.e., <code>rand(seed)</code> using which the pseudo-random numbers are generated. These functions internally use the <code>java.util.Random</code> class.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<DOUBLE> math:rand()
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">An optional seed value that will be used to generate the random number sequence.</p></td>
        <td style="vertical-align: top">defaultSeed</td>
        <td style="vertical-align: top">INT<br>LONG</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">In the example given above, a random double value between 0 and 1 will be generated using math:rand().</p>
<p></p>
### round *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the value of the input argument rounded off to the closest integer/long value.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value that should be rounded off to the closest integer/long value.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">The function rounds off 'inValue1' to the closest int/long value and directs the output to the output stream, 'OutMediationStream'. For example, round(3252.353) returns 3252.</p>
<p></p>
### signum *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns +1, 0, or -1 for the given positive, zero and negative values respectively. This function wraps the <code>java.lang.Math.signum()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value that should be checked to be positive, negative or zero.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">The function evaluates the 'inValue' given to be positive, negative or zero and directs the result to the output stream, 'OutMediationStream'. For example, signum(-6.32d) returns -1.</p>
<p></p>
### sin *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns the sine of the value given in radians. This function wraps the <code>java.lang.Math.sin()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose sine value should be found. Input is required to be in radians.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">The function calculates the sine value of the given 'inValue' and directs the output to the output stream, 'OutMediationStream. For example, sin(6d) returns -0.27941549819892586.</p>
<p></p>
### sinh *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns the hyperbolic sine of the value given in radians. This function wraps the <code>java.lang.Math.sinh()</code> function. </p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose hyperbolic sine value should be found. Input is required to be in radians.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function calculates the hyperbolic sine value of 'inValue' and directs the output to the output stream, 'OutMediationStream'. For example, sinh(6d) returns 201.71315737027922.</p>
<p></p>
### sqrt *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the square-root of the given value. It wraps the <code>java.lang.Math.sqrt()</code>s function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose square-root value should be found.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">The function calculates the square-root value of the 'inValue' and directs the output to the output stream, 'OutMediationStream'. For example, sqrt(4d) returns 2.</p>
<p></p>
### tan *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the tan of the given value in radians. It wraps the <code>java.lang.Math.tan()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose tan value should be found. Input is required to be in radians.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function calculates the tan value of the 'inValue' given and directs the output to the output stream, 'OutMediationStream'. For example, tan(6d) returns -0.29100619138474915.</p>
<p></p>
### tanh *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function returns the hyperbolic tangent of the value given in radians. It wraps the <code>java.lang.Math.tanh()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the parameter whose hyperbolic tangent value should be found. Input is required to be in radians.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">If the 'inVaue' in the input stream is given, this function calculates the hyperbolic tangent value of the same and directs the output to 'OutMediationStream' stream. For example, tanh(6d) returns 0.9999877116507956.</p>
<p></p>
### toDegrees *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function converts the value given in radians to degrees. It wraps the <code>java.lang.Math.toDegrees()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The input value in radians that should be converted to degrees.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">The function converts the 'inValue' in the input stream from radians to degrees and directs the output to 'OutMediationStream' output stream. For example, toDegrees(6d) returns 343.77467707849394.</p>
<p></p>
### toRadians *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function converts the value given in degrees to radians. It wraps the <code>java.lang.Math.toRadians()</code> function.</p>
<p></p>
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
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The input value in degrees that should be converted to radians.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>FLOAT<br>DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
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
<p></p>
<p style="word-wrap: break-word;margin: 0;">This function converts the input, from degrees to radians and directs the result to 'OutMediationStream' output stream. For example, toRadians(6d) returns 0.10471975511965977.</p>
<p></p>
