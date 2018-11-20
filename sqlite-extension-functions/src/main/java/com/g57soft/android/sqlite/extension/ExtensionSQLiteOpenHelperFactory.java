package com.g57soft.android.sqlite.extension;

import java.util.ArrayList;
import java.util.List;

import io.requery.android.database.sqlite.RequerySQLiteOpenHelperFactory;
import io.requery.android.database.sqlite.SQLiteCustomExtension;
import io.requery.android.database.sqlite.SQLiteDatabaseConfiguration;

/**
 * Get extension configuration options
 * Created by Ihor Havryliuk on 20.11.2018.
 */
public class ExtensionSQLiteOpenHelperFactory {

    public Iterable<RequerySQLiteOpenHelperFactory.ConfigurationOptions> getExtensionConfigOptions( final SQLiteCustomExtension extension ){

        final List<RequerySQLiteOpenHelperFactory.ConfigurationOptions> configurationOptions = new ArrayList<>(2);
        final RequerySQLiteOpenHelperFactory.ConfigurationOptions extensionOption = new RequerySQLiteOpenHelperFactory.ConfigurationOptions() {
            @Override
            public SQLiteDatabaseConfiguration apply(SQLiteDatabaseConfiguration configuration) {
                configuration.customExtensions.add( extension );
                return configuration;
            }
        };
        configurationOptions.add( extensionOption );

        return configurationOptions;
    }

}
