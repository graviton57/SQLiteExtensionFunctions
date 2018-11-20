# SQLite Extension functions


## What?

The purpose of this project is to use the Android NDK to create SQLite extensions functions  packaged as an AAR library distributed on jcenter.

### Extensions functions

This extension will provide common mathematical and string functions in SQL queries using the operating system libraries or provided definitions.

References: http://www.sqlite.org/contrib/download/extension-functions.c?get=25

This library will provide common mathematical and string functions in
SQL queries using the operating system libraries or provided
definitions.  It includes the following functions:

Math: acos, asin, atan, atn2, atan2, acosh, asinh, atanh, difference,
degrees, radians, cos, sin, tan, cot, cosh, sinh, tanh, coth, exp,
log, log10, power, sign, sqrt, square, ceil, floor, pi.

String: replicate, charindex, leftstr, rightstr, ltrim, rtrim, trim,
replace, reverse, proper, padl, padr, padc, strfilter.

Aggregate: stdev, variance, mode, median, lower_quartile,
upper_quartile.

The string functions ltrim, rtrim, trim, replace are included in
recent versions of SQLite and so by default do not build.


## How to use

Add the line below to the dependencies section in the ```build.gradle``` :
```
dependencies {
         implementation 'com.github.graviton57:sqlite-extension-functions:1.0'
}
```

Follow the steps below for how to use a extension in your project:

    Extend your SQLiteOpenHelper and override createConfiguration method:
        
    @Override
        protected SQLiteDatabaseConfiguration createConfiguration( final String path, final int openFlags ) {
            final SQLiteDatabaseConfiguration config = super.createConfiguration( path, openFlags );
            config.customExtensions.add( new SQLiteExtensionFunctions().getExtension( context.getApplicationInfo()) );
            return config;
        }
        
    or extend your RoomDatabase  :
    
    public static synchronized SalaryDatabase getInstance( final Context context ) {
            
            final SQLiteCustomExtension extension = 
                    new SQLiteExtensionFunctions().getExtension( context.getApplicationInfo());
            final Iterable<RequerySQLiteOpenHelperFactory.ConfigurationOptions> configurationOptions =
                    new ExtensionSQLiteOpenHelperFactory().getExtensionConfigOptions( extension );
            
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context.getApplicationContext(), SalaryDatabase.class, "salaries.db")
                        .openHelperFactory( new RequerySQLiteOpenHelperFactory( configurationOptions ) )
                        .build();
            }
            return sInstance;
        }       
    
CPU Architectures
-----------------

The native library is built for the following CPU architectures:

- `armeabi-v7a`
- `arm64-v8a`
- `x86`
- `x86_64`

However you may not want to include all binaries in your apk. You can exclude certain variants by
using `packagingOptions`:

```gradle
android {
    packagingOptions {
        exclude 'lib/arm64-v8a/libsqlitefunctions.so'
        exclude 'lib/x86/libsqlitefunctions.so'
        exclude 'lib/x86_64/libsqlitefunctions.so'
        exclude 'lib/mips/libsqlitefunctions.so'
    }
}
```        


License
=======

    Copyright 2018 Igor Gavrilyuk.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




   