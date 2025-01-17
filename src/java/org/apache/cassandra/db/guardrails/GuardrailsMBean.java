/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.cassandra.db.guardrails;

import java.util.Set;

/**
 * JMX entrypoint for updating the default guardrails configuration parsed from {@code cassandra.yaml}.
 * <p>
 * This is different to just exposing {@link GuardrailsConfig} in that the methods here should be JMX-friendly.
 *
 * <p>For consistency, guardrails based on a simple numeric threshold should use the naming scheme
 * {@code <whatIsGuarded>WarnThreshold} for soft limits and {@code <whatIsGuarded>AbortThreshold} for hard
 * ones, and if the value has a unit, that unit should be added at the end (for instance,
 * {@code <whatIsGuarded>AbortThresholdInKb}). For "boolean" guardrails that disable a feature, use
 * {@code <whatIsGuardedEnabled}. Other type of guardrails can use appropriate suffixes but should start with
 * {@code <whatIsGuarded>}.
 */
public interface GuardrailsMBean
{
    /**
     * Whether guardrails are enabled or not.
     *
     * @return {@code true} if guardrails are enabled, {@code false} otherwise
     */
    boolean getEnabled();

    /**
     * Enable/disable guardrails.
     *
     * @param enabled {@code true} for enabling guardrails, {@code false} for disabling them.
     */
    void setEnabled(boolean enabled);

    /**
     * @return The threshold to warn when creating more tables than threshold.
     * -1 means disabled.
     */
    int getTablesWarnThreshold();

    /**
     * @return The threshold to prevent creating more tables than threshold.
     * -1 means disabled.
     */
    int getTablesAbortThreshold();

    /**
     * @param warn The threshold to warn when creating more tables than threshold. -1 means disabled.
     * @param abort The threshold to prevent creating more tables than threshold. -1 means disabled.
     */
    void setTablesThreshold(int warn, int abort);

    /**
     * @return The threshold to warn when having more columns per table than threshold.
     * -1 means disabled.
     */
    int getColumnsPerTableWarnThreshold();

    /**
     * @return The threshold to prevent having more columns per table than threshold. -1 means disabled.
     */
    int getColumnsPerTableAbortThreshold();

    /**
     * @param warn The threshold to warn when having more columns per table than threshold. -1 means disabled.
     * @param abort The threshold to prevent having more columns per table than threshold. -1 means disabled.
     */
    void setColumnsPerTableThreshold(int warn, int abort);

    /**
     * @return The threshold to warn when creating more secondary indexes per table than threshold. -1 means disabled.
     */
    int getSecondaryIndexesPerTableWarnThreshold();

    /**
     * @return The threshold to prevent creating more secondary indexes per table than threshold. -1 means disabled.
     */
    int getSecondaryIndexesPerTableAbortThreshold();

    /**
     * @param warn The threshold to warn when creating more secondary indexes per table than threshold. -1 means disabled.
     * @param abort The threshold to prevent creating more secondary indexes per table than threshold. -1 means disabled.
     */
    void setSecondaryIndexesPerTableThreshold(int warn, int abort);

    /**
     * @return The threshold to warn when creating more materialized views per table than threshold.
     * -1 means disabled.
     */
    int getMaterializedViewsPerTableWarnThreshold();

    /**
     * @return The threshold to prevent creating more materialized views per table than threshold.
     * -1 means disabled.
     */
    int getMaterializedViewsPerTableAbortThreshold();

    /**
     * @param warn The threshold to warn when creating more materialized views per table than threshold. -1 means disabled.
     * @param abort The threshold to prevent creating more materialized views per table than threshold. -1 means disabled.
     */
    void setMaterializedViewsPerTableThreshold(int warn, int abort);

    /**
     * @return properties that are not allowed when creating or altering a table.
     */
    Set<String> getTablePropertiesDisallowed();

    /**
     * @return Comma-separated list of properties that are not allowed when creating or altering a table.
     */
    String getTablePropertiesDisallowedCSV();

    /**
     * @param properties properties that are not allowed when creating or altering a table.
     */
    void setTablePropertiesDisallowed(Set<String> properties);

    /**
     * @param properties Comma-separated list of properties that are not allowed when creating or altering a table.
     */
    void setTablePropertiesDisallowedCSV(String properties);

    /**
     * @return properties that are ignored when creating or altering a table.
     */
    Set<String> getTablePropertiesIgnored();

    /**
     * @return Comma-separated list of properties that are ignored when creating or altering a table.
     */
    String getTablePropertiesIgnoredCSV();

    /**
     * @param properties properties that are ignored when creating or altering a table.
     */
    void setTablePropertiesIgnored(Set<String> properties);

    /**
     * @param properties Comma-separated list of properties that are ignored when creating or altering a table.
     */
    void setTablePropertiesIgnoredCSV(String properties);

    /**
     * Returns whether user-provided timestamps are allowed.
     *
     * @return {@code true} if user-provided timestamps are allowed, {@code false} otherwise.
     */
    boolean getUserTimestampsEnabled();

    /**
     * Sets whether user-provided timestamps are allowed.
     *
     * @param enabled {@code true} if user-provided timestamps are allowed, {@code false} otherwise.
     */
    void setUserTimestampsEnabled(boolean enabled);
}
