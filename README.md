# System and Custom Fonts in Java ME

Sample code from: https://developer.samsung.com/java/technical-docs/System-Custom-Fonts-in-Java-ME

![alt text](https://github.com/woodie/J2meFonts/blob/master/docs/system.png?raw=true) &nbsp;
![alt text](https://github.com/woodie/J2meFonts/blob/master/docs/custon.png?raw=true)

I like the simplicity of the source as an image, not a Windows FNT or Java resource file. Unfortunately, capturing the font metrics is a lot of work. To be useful, this library would need to colorize the font; the source image here is white and difficual to work with. I also prefer not to store starting points, but instead index into a font matrix using the ASCII char value.
