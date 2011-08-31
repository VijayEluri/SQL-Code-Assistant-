header {
package com.deepsky.generated.plsql.adopted;
}


class PLSqlParserAdopted extends Parser;

options {
    importVocab = PLSql2;
    exportVocab = PLSql;
    k = 3;
    buildAST = true;
}

tokens {
    START_RULE;
    ERROR_TOKEN_A;
    PACKAGE_SPEC; PACKAGE_BODY; PACKAGE_NAME; PACKAGE_OBJ_BODY;
    RECORD_TYPE_DECL;
    SELECT_EXPRESSION; SELECT_EXPRESSION_UNION;
    PLSQL_BLOCK;
///    BEGIN_BLOCK;
    CURSOR_DECLARATION;
    VARIABLE_DECLARATION;
    PROCEDURE_BODY;
    FUNCTION_BODY;
    PARAMETER_SPEC;
    SQL_STATEMENT;
    IF_STATEMENT;
    LOOP_STATEMENT;
    STATEMENT;
//    SELECT_COMMAND;
    SELECT_LIST;
    TABLE_REFERENCE_LIST;
    TABLE_REFERENCE_LIST_FROM;
    WHERE_CONDITION;
    SUBQUERY;
    SQL_IDENTIFIER;
    FUNCTION;
    GROUP_FUNCTION;
    USER_FUNCTION;
    MULTIPLY;
    ARGUMENT;
    ARGUMENT_LIST;
    FIELD_NAME;
    PLSQL_EXPR_LIST; EXPR_LIST;
    DECLARE_LIST;
    FUNCTION_CALL;
    PACKAGE_INIT_SECTION;
    CONCAT;
    CALL_ARGUMENT_LIST;
    CALL_ARGUMENT;
    BASE_EXRESSION;
    COLUMN_SPEC;
    LOGICAL_AND;
    LOGICAL_OR;
    CASE_EXPRESSION_SMPL;
    CASE_EXPRESSION_SRCH;
    CASE_STATEMENT;
    COUNT_FUNC;
    TRIM_FUNC;
    COLUMN_SPEC_LIST;
    INSERT_COMMAND; UPDATE_COMMAND; DELETE_COMMAND; MERGE_COMMAND; MERGE_WHEN_COMMAND;
    STRING_LITERAL; NUMERIC_LITERAL; BOOLEAN_LITERAL;
    RETURN_TYPE;
    TYPE_SPEC;
    VARIABLE_NAME;
    COLUMN_OUTER_JOIN;
    LOGICAL_FACTOR;
    TABLE_ALIAS;
    CAST_EXPR;
    VAR_REF; PLSQL_VAR_REF; PARAMETER_REF;
    EXCEPTION_SECTION;

    SELECTED_TABLE;
    CREATE_PROCEDURE;
    CREATE_FUNCTION;

    COMMIT_STATEMENT; ROLLBACK_STATEMENT;
    NULL_STATEMENT;
    ASSIGNMENT_STATEMENT;
    PROCEDURE_CALL;
    RETURN_STATEMENT;

    OBJECT_NAME;
    PARAMETER_NAME;
    DEFAULT;
//    CONDITION;

    THEN_STATEMENT;
    ELSIF_STATEMENT;
    ELSE_STATEMENT;

    STATEMENT_LIST;

    RELATION_CONDITION;
    ISNULL_CONDITION;
    LIKE_CONDITION;
    BETWEEN_CONDITION;
    IN_CONDITION;
    PARENTHESIZED_EXPR;

    ORDER_CLAUSE;
    CONNECT_CLAUSE;
    GROUP_CLAUSE;
    INTO_CLAUSE;
    FOR_UPDATE_CLAUSE;

    ASTERISK1;
    ROWCOUNT_EXRESSION;
    MULTIPLICATION_OP;
    DIVIDE_OP;
    PLUS_OP;
    MINUS_OP;
    CONCAT_OP;

    CURSOR_NAME;
    DATATYPE;
    EXTRACT_DATE_FUNC;
    ANSI_JOIN_TAB_SPEC;
    // DAY_TO_EXPR;
    INTERVAL_DAY_TO_SEC_EXPR;
    INTERVAL_CONST;
    USER_CONST;
    SYSDATE_CONST;
    SYSTIMESTAMP_CONST; CURRENT_TIMESTAMP_CONST;
    ALIAS_NAME;
    EXISTS_EXPR;
    SUBQUERY_EXPR;
    TYPE_NAME_REF;
    TYPE_NAME;
    SORTED_DEF;
    RAISE_STATEMENT;
    PACKAGE_OBJ_SPEC;
    PROCEDURE_SPEC;
    SIMPLE_UPDATE_COMMAND;
    SUBQUERY_UPDATE_COMMAND;
    IMMEDIATE_COMMAND;
    FUNCTION_SPEC;
    NEGATE_FACTOR;
    PRAGMA;
    TABLE_TYPE_REF;
    COLUMN_TYPE_REF;
    STATEMENT_ANNOT;
    ASTERISK_COLUMN;
    IDENT_ASTERISK_COLUMN;
    EXPR_COLUMN;
    TABLE_NAME; TABLE_NAME_WITH_LINK;
    FROM_SUBQUERY;
    FROM_PLAIN_TABLE;
    SCHEMA_NAME;
    TABLE_REF;
    COLUMN_NAME_REF;
    ARITHMETIC_EXPR;
    ASSIGNMENT_OP;
    DBTIMEZONE;
    SUBQUERY_CONDITION;
    RECORD_ITEM;
    EXCEPTION_DECL;
    EXCEPTION_PRAGMA;
    COMPLEX_NAME;
    RESTRICT_REF_PRAGMA;
    CHARACTER_SET;
    AUTHID;
    FIPSFLAG_PRAGMA; BUILTIN_PRAGMA; INTERFACE_PRAGMA; TIMESTAMPG_PRAGMA;
    TIMESTAMP_CONST;
    SUBTYPE_DECL;
    MEMBER_OF;

    SQLPLUS_SET; SQLPLUS_COMMAND; SQLPLUS_RUNSCRIPT;
    SQLPLUS_SHOW; SQLPLUS_DEFINE; SQLPLUS_VARIABLE; SQLPLUS_EXEC; SQLPLUS_WHENEVER;
    SQLPLUS_PROMPT; SQLPLUS_COLUMN; SQLPLUS_START;
    SQLPLUS_SERVEROUTPUT;  SQLPLUS_REPFOOTER;  SQLPLUS_REPHEADER;
    SQLPLUS_EXIT; SQLPLUS_QUIT; SQLPLUS_RUNSCRIPT; SQLPLUS_SPOOL;

    OR_LOGICAL; AND_LOGICAL; NOT_LOGICAL; LOGICAL_EXPR; RELATION_OP;
    SEQUENCE_EXPR;
    ALIAS_IDENT;
    COLUMN_NAME_DDL; COLUMN_DEF; TABLE_DEF;
    TABLE_COLLECTION; VARRAY_COLLECTION;
    REF_CURSOR;
    OBJECT_TYPE_DEF;

    AT_TIME_ZONE_EXPR;
    TIMEZONE_SPEC;

    ALTER_TABLE;
    CREATE_TEMP_TABLE;
    COMMENT; COMMENT_STR;
    CREATE_INDEX;
    INSERT_INTO_SUBQUERY_COMMAND;

    CONDITIONAL_COMPILATION;
    COND_COMP_SEQ;
    COND_COMP_SEQ2;
    COND_COMP_ERROR;
    COLUMN_NAME_LIST;

    SERIALLY_REUSABLE_PRAGMA;
    CREATE_VIEW;
    VIRTUAL_TABLE;
    ROWNUM;
    CUSTOM_AGGR_FUNCTION;
    LAST_STMT_RESULT_NUM;
    LAST_STMT_RESULT_BOOL;
    CURRENT_OF_CLAUSE;
    CURSOR_STATE;
    SQLCODE_SYSVAR;
    SQLERRM_SYSVAR;
    COLLECTION_METHOD_CALL; C_RECORD_ITEM_REF;
    CALLABLE_NAME_REF;
    TRUNCATE_TABLE;
    DROP_VIEW; DROP_TABLE; DROP_FUNCTION; DROP_PROCEDURE; DROP_PACKAGE; DROP_INDEX; DROP_SEQUENCE; DROP_TYPE;
    DROP_OPERATOR; DROP_SYNONYM; DROP_USER; DROP_ROLE; DROP_LIBRARY; DROP_DB_LINK; DROP_DIRECTORY;

    COLUMN_PK_SPEC;
    COLUMN_FK_SPEC;
    NOT_NULL_STMT;
    CONSTRAINT;
    PK_SPEC; FK_SPEC;
    UNIQUE_CONSTRAINT;
    CONSTRAINT_NAME;
    ADD_CONSTRAINT;
    ADD_PRIMARY_KEY;

    IOT_TYPE; HEAP_ORGINIZED; EXTERNAL_TYPE;

    //
    NAME_FRAGMENT;
    COLLECTION_METHOD_NAME;
    V_COLUMN_DEF;
    TABLE_NAME_DDL; VIEW_NAME; COMMENT_STR;
    INDEX_NAME; VIEW_NAME_DDL;
//    SEQUENCE_NAME;

    SQLPLUS_PROMPT;
    CREATE_TRIGGER; CREATE_DIRECTORY; CREATE_DB_LINK; CREATE_SEQUENCE;
    TRIGGER_FOR_EACH; TRIGGER_NAME; ALTER_TRIGGER;
    DB_EVNT_TRIGGER_CLAUSE;
    DDL_TRIGGER_CLAUSE;
    DML_TRIGGER_CLAUSE;
    TRIGGER_COLUMN_REF;
    INSTEADOF_TRIGGER;
    TRIGGER_TARGET;

    NUMERIC_LOOP_SPEC; FORALL_LOOP_SPEC; CURSOR_REF_LOOP_SPEC; CURSOR_IMPL_LOOP_SPEC;
//    CURSOR_LOOP_SPEC;
    LOOP_INDEX;
    RECORD_ITEM_NAME;
//    FORALL_STATEMENT;
    GOTO_STATEMENT;
    EXIT_STATEMENT;
    LABEL_NAME;
}

{
    protected int depth = 0;
    protected int returnType = -1;
    public void __markRule(int type){
        returnType = type;
    }

    final int _NO_WS_ = 0;
    final int _KEEP_WS_ = 1;
    final int _SINGLE_WS_ = 2;
    final int _NEWLINE_WS_ = 3;

    public void handle_ws( int action ){
    }

    protected void process_wrapped_package(String package_name){
        // default action if the package is wrapped
        throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
    }
}

// dirty hack, should be fixed ASAP ----------------
no_one_should_call_me:
    identifier
    {  __markRule(ERROR_TOKEN_A);}
    ;
/*
start_rule:
    (
        (create_or_replace (DIVIDE!)?)
        | package_spec
            {  __markRule(PACKAGE_SPEC); }
        | (function_body (DIVIDE!)?)
        | (procedure_body (DIVIDE!)?)
        | (sql_statement (SEMI!)?)
        | ("alter") => (alter_command (SEMI!)?)
        | (comment (SEMI!)?)
        | (type_definition (SEMI!)?)
        | ( drop_command (SEMI!)?)
        | (sqlplus_command_internal)
    )* EOF!
    ;
*/

// -------------------------------------------------

// IMPORTANT: code generated by ANTLR for start_rule should be revised because it supposed a particual refereences being generated  
start_rule:
    (   start_rule_inner
        | {
              if (LA(1)==EOF) {
                    match(EOF);
                    break;
              } else {
                    // consume();
                    // consumeUntil(_tokenSet_2);
                    recover(null,_tokenSet_2);
              }
        }
    )*
    ;

start_rule_inner:
        (create_or_replace (DIVIDE!)?)
        | package_spec
            {  __markRule(PACKAGE_SPEC); }
        | (function_body (DIVIDE!)?)
        | (procedure_body (DIVIDE!)?)
        | (sql_statement (SEMI!)?)
        | ("alter") => (alter_command (SEMI!)?)
        | (comment (SEMI!)?)
        | (type_definition (SEMI!)?)
        | ( drop_command (SEMI!)?)
        | ( truncate_command (SEMI!)?)
        | sqlplus_command_internal
    ;


drop_command:
    "drop"! (
        ("table"! table_name_ddl ("purge")?)
            {  __markRule(DROP_TABLE);}
        | ("view" table_name_ddl ("cascade" "constraints")?)
            {  __markRule(DROP_VIEW);}
        | ("function" callable_name_ref)
            {  __markRule(DROP_FUNCTION);}
        | ("procedure" callable_name_ref)
            {  __markRule(DROP_PROCEDURE);}
        | ("package" ("body")? (schema_name DOT!)? package_name )  // todo - "package_name" should be replaced with "package_name_ref"
            {  __markRule(DROP_PACKAGE);}
        | ("index" (schema_name DOT!)? index_name ("force")? )
            {  __markRule(DROP_INDEX);}
        | ("sequence" object_name )
            {  __markRule(DROP_SEQUENCE);}
// todo - "column_name_ref" should be replaced with "type_name_ref"
        | ("type" ("body")? object_name ("force"|"validate")? )
            {  __markRule(DROP_TYPE);}
        | ( ("public")? "synonym" object_name  )
            {  __markRule(DROP_SYNONYM);}
        | ("operator"  object_name  ("force")? )
            {  __markRule(DROP_OPERATOR);}
        | ("user"  object_name ("cascade")? )
            {  __markRule(DROP_USER);}
        | ("role"  object_name )
            {  __markRule(DROP_ROLE);}
        | ("directory"  object_name )
            {  __markRule(DROP_DIRECTORY);}
        | ("library"  object_name )
            {  __markRule(DROP_LIBRARY);}
        | ("database" "link" object_name)
            {  __markRule(DROP_DB_LINK);}
     )
    ;

truncate_command:
    "truncate"! "table"! table_name_ddl
    {  __markRule(TRUNCATE_TABLE);}
    ;

comment:
    "comment"! "on"! (
        ("table"! table_name_ddl "is"! comment_string)
        | ("column"! table_name_ddl DOT column_name_ddl "is"! comment_string)
     )
    {  __markRule(COMMENT);}
    ;

comment_string:
    string_literal
    {  __markRule(COMMENT_STR);}
    ;

column_def:
//    column_name_ddl datatype (not_null|("default" ("sysdate"|numeric_literal)))?
    column_name_ddl type_spec ("default" ("sysdate"|numeric_literal|string_literal))? (not_null)?
    (pk_spec|fk_spec|column_constraint)?
    {  __markRule(COLUMN_DEF);}
    ;

not_null:
     ("not")? "null"
    {  __markRule(NOT_NULL_STMT);}
    ;

row_movement_clause:
    ("disable"|"enable") "row" "movement"
    ;

pk_spec:
    "primary" "key" ("disable"|"enable")?
    {  __markRule(COLUMN_PK_SPEC);}
    ;

fk_spec:
    "references"! table_name_ddl OPEN_PAREN! column_name_ddl CLOSE_PAREN!
    {  __markRule(COLUMN_FK_SPEC);}
    ;

column_constraint:
    "constraint" identifier (
        not_null
// todo        | ("unique" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
        | ("primary" "key" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN ("disable"|"enable")? )
// todo        | ("check" condition )
    )
    ;

sqlplus_command_internal:
    (sqlplus_command)+
    {  __markRule(SQLPLUS_COMMAND);}
    ;

sqlplus_command:
    ("set" (identifier2|"long")? (identifier2|numeric_literal|string_literal)*   ( SEMI! )*)
        {  __markRule(SQLPLUS_SET);}
    | ("show"  identifier ( base_expression ) *  ( SEMI! )*)
        {  __markRule(SQLPLUS_SHOW);}
    | (("var"|"variable")  identifier2 datatype  ( SEMI! )*)
        {  __markRule(SQLPLUS_VARIABLE);}
    | (("col"|"column")  identifier2 (identifier2)*  ( SEMI! )*)
        {  __markRule(SQLPLUS_COLUMN);}
    | (("exec"|"execute")  sqlplus_exec_statement ( SEMI! )*)
        {  __markRule(SQLPLUS_EXEC);}
    | ("whenever" ("sqlerror"| "oserror") ("exit"|"continue"|"commit"|"rollback"|"none") (SEMI!)* )
        {  __markRule(SQLPLUS_WHENEVER);}
    | (("def"|"define") identifier2 (EQ (plsql_expression | DOUBLE_QUOTED_STRING | STORAGE_SIZE))? )
        {  __markRule(SQLPLUS_DEFINE);}
    | ("prompt" (~CUSTOM_TOKEN)* CUSTOM_TOKEN)
        {  __markRule(SQLPLUS_PROMPT);}
    | "rem" (~CUSTOM_TOKEN)* CUSTOM_TOKEN
        {  __markRule(SQLPLUS_PROMPT);}
    | "exit"  (identifier2|numeric_literal)?
        {  __markRule(SQLPLUS_EXIT);}
    | ("quit" (SEMI!)?)
        {  __markRule(SQLPLUS_QUIT);}
    | "spool" ( "off"|(identifier4 (DOT! identifier4)*) )
        {  __markRule(SQLPLUS_SPOOL);}
    | ( ("sta"|"start") sqlplus_path )    // identifier4 (DOT! identifier4)* )
        {  __markRule(SQLPLUS_START);}
    | ( "repfooter" ("off"|"on"|("is" "null")) (SEMI!)?)
        {  __markRule(SQLPLUS_REPFOOTER);}
    | ( "repheader" ("off"|"on"|("is" "null")) (SEMI!)?)
        {  __markRule(SQLPLUS_REPHEADER);}
    | ( "serveroutput" ("off"|"on") (SEMI!)?)
        {  __markRule(SQLPLUS_SERVEROUTPUT);}

    | ("begin"|"declare") => (begin_block (SEMI!)? (DIVIDE!)? )
    | (AT_PREFIXED (~CUSTOM_TOKEN)* CUSTOM_TOKEN )
        {  __markRule(SQLPLUS_RUNSCRIPT);}
    ;


sqlplus_exec_statement:
    ( plsql_lvalue ASSIGNMENT_EQ) => assignment_statement
    | ( procedure_call ( DOT procedure_call )*)
    ;

sqlplus_path:
    (DOUBLE_DOT|DOT|identifier3) ((DIVIDE|BACKSLASH) (DOUBLE_DOT|DOT|identifier3))*
    ;


create_or_replace:
    "create"! ( "or"! "replace"! )? ("force")?
    (
        package_spec
            {  __markRule(PACKAGE_SPEC); }
        | package_body
        | procedure_body
            {  __markRule(CREATE_PROCEDURE);}
        | function_body
            {  __markRule(CREATE_FUNCTION);}
        | create_view
            {  __markRule(CREATE_VIEW);}
        | (type_definition (SEMI!)?)
        | (create_table2 (SEMI!)?)
            {  __markRule(TABLE_DEF);}
        | (create_temp_table2 (SEMI!)?)
            {  __markRule(CREATE_TEMP_TABLE);}
        | (create_index2 (SEMI!)?)
            {  __markRule(CREATE_INDEX);}
        | (create_trigger (SEMI!)?)
            {  __markRule(CREATE_TRIGGER);}
        | (create_directory (SEMI!)?)
            {  __markRule(CREATE_DIRECTORY);}
        | (create_db_link (SEMI!)?)
            {  __markRule(CREATE_DB_LINK);}
        | (create_sequence (SEMI!)?)
            {  __markRule(CREATE_SEQUENCE);}
    )
    ;

/*
CREATE SEQUENCE T1_SEQ
  START WITH 100000
  INCREMENT BY 2
  MINVALUE 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  CYCLE
  CACHE 25
  NOORDER;
*/
create_sequence:
    "sequence"! object_name (sequence_opt)*
    ;

sequence_opt:
    ("maxvalue" numeric_literal)
    | ("minvalue" numeric_literal)
    | "cycle"
    | "nocycle"
    | ("cache" numeric_literal)
    | "nocache"
    | ("increment" "by" numeric_literal)
    | ("start" "with" numeric_literal)
    | "order"
    | "noorder"
    ;


create_db_link:
    "database" "link" object_name
    "connect" "to" identifier2 "identified" "by" DOUBLE_QUOTED_STRING
    ("using" (identifier2|string_literal))?
    ;

// -------------------------------------------------------------------
// [TRIGGER START] ----------------------------------
// -------------------------------------------------------------------

create_trigger:
    "trigger"! trigger_name
    // BEFORE INSERT OR UPDATE ON xdv_dev_grp_mobile_station_it
    // AFTER INSERT OR UPDATE OR DELETE ON xdv_dev_grp_mobile_station_it
    // FOR EACH ROW
    (   ( ("after"|"before")
            (
                dml_trigger
                | ddl_trigger
                | db_event_trigger
            )
        )
        | instead_of_trigger
    )
    (for_each|referencing_old_new)* (trigger_when)? begin_block
    ;

db_event_trigger:
    ("startup" | "shutdown" | "servererror" | "logon" | "logoff")
    "on"! trigger_target
    {  __markRule(DB_EVNT_TRIGGER_CLAUSE);}
    ;

ddl_trigger:
    ("create" | "alter" | "drop" | "analyze" | ("associate" "statistics") | "audit" | "noaudit"
    | "comment" | "ddl" | ("diassociate" "statistics") | "grant" | "rename" | "revoke" | "truncate")
    "on"! trigger_target
    {  __markRule(DDL_TRIGGER_CLAUSE);}
    ;

dml_trigger:
    insert_update_delete ( "or"! insert_update_delete)* "on"! table_name_ddl
    {  __markRule(DML_TRIGGER_CLAUSE);}
    ;

trigger_target:
    "schema" | "database"
    {  __markRule(TRIGGER_TARGET);}
    ;

instead_of_trigger:
    "instead"! "of"! ("delete"|"insert"|"update") "on"! view_name_ddl
    {  __markRule(INSTEADOF_TRIGGER);}
    ;

for_each:
    "for"! "each"! "row"!
    {  __markRule(TRIGGER_FOR_EACH);}
    ;

referencing_old_new:
    "referencing" (("old" "as" identifier2) | ("new" "as" identifier2))*
    ;
trigger_when:
    "when"! condition
    ;

insert_update_delete:
    "insert"
    | ("update" ("of" identifier2 (COMMA! identifier2)*)? )
    | "delete"
    ;

trigger_name:
    identifier2
    {  __markRule(TRIGGER_NAME);}
    ;

alter_trigger:
    "alter"! "trigger"! trigger_name enable_disable_clause
    {  __markRule(ALTER_TRIGGER);}
    ;
// -------------------------------------------------------------------
// [TRIGGER END] ----------------------------------
// -------------------------------------------------------------------


create_index2:
    ("unique"|"bitmap")? "index"! index_name "on"! table_name_ddl
    OPEN_PAREN index_column_spec_list CLOSE_PAREN
    (physical_properties|table_properties)*
    ;

index_column_spec_list:
    identifier2 ("asc"|"desc")? (COMMA! identifier2 ("asc"|"desc")?)*
    ;


create_directory:
    "directory" object_name ("as" string_literal)?
    ;
// -------------------------------------------------------------------
// [CREATE TABLE START] ----------------------------------
// -------------------------------------------------------------------

create_table2:
    "table"! (schema_name DOT!)? table_name_ddl
    (OPEN_PAREN! column_def (COMMA! column_def)* (COMMA! constaraint)* CLOSE_PAREN!)?
    (organization_spec)? (physical_properties|table_properties)*
    ("as" select_expression)?
    ;

create_temp_table2:
    ("global")? "temporary"! "table"! table_name_ddl
    (OPEN_PAREN! column_def (COMMA! column_def)* (COMMA! constaraint)* CLOSE_PAREN!)?
    ("on" "commit" ("preserve" | "delete") "rows" )
    (cache_clause)?
    ("as" select_expression)?
    ;

physical_properties:
    segment_attributes_clause
//    | organization_spec
//    | cluster_clause 
    ;

segment_attributes_clause:
    physical_attributes_clause
    | ("tablespace" identifier2)
    | "online"
    | ("compute" "statistics" ("parallel"|"noparallel"|identifier2)? )
    | logging_clause
    | table_compression
    ;

logging_clause:
    "logging"
    | "nologging"
    | "filesystem_like_logging"
    ;

table_compression:
    ("compress" (numeric_literal | ("for" ("all"|"direct_load") "operations"))?)
    | "nocompress"
    ;

physical_attributes_clause:
    ("pctfree" numeric_literal)
    | ("pctused" numeric_literal)
    | ("initrans" numeric_literal)
    | ("maxtrans" numeric_literal)
    | storage_spec
    ;

table_properties:
    table_partitioning_clause
    | cache_clause
    | parallel_clause
    | alter_table_options
    | row_movement_clause
    | monitoring_clause
    ;

cache_clause:
    "cache"|"nocache"
    ;

monitoring_clause:
    "monitoring" | "nomonitoring"
    ;

table_partitioning_clause:
    range_partitions
    | hash_partitions
    | local_partitioned_index // -- "create index" specific
// todo   | list_partitions
// todo   | reference_partitions
// todo   | composite_range_partitions
// todo   | composite_list_partitions
// todo  | system_partitioning
    ;

range_partitions:
    "partition" "by" "range" OPEN_PAREN! identifier2 (COMMA! identifier2)* CLOSE_PAREN!
    ("interval" plsql_expression ("store" "in" OPEN_PAREN identifier2 (COMMA! identifier2)* OPEN_PAREN)?)?
    OPEN_PAREN partition_item (COMMA! partition_item)* CLOSE_PAREN
    ;

partition_item:
    "partition" (identifier2)? range_values_clause (table_partition_description)+
    ;

range_values_clause:
    "values" "less" "than" OPEN_PAREN (numeric_literal | "maxvalue") (COMMA! (numeric_literal | "maxvalue"))* CLOSE_PAREN
    ;

table_partition_description:
    segment_attributes_clause
    | table_compression
    | "overflow"
// todo  | lob_storage_clause
    ;

hash_partitions:
    "partition" "by" "hash" OPEN_PAREN! identifier2 (COMMA! identifier2)* CLOSE_PAREN!
    (individual_hash_partitions | hash_partitions_by_quantity)
    ;

individual_hash_partitions:
    OPEN_PAREN "partition" (identifier2)? (partition_storage_clause)* (COMMA! "partition" (identifier2)? (partition_storage_clause)* )* CLOSE_PAREN
    ;

partition_storage_clause:
    ("tablespace" identifier2)
    | "overflow"
    | table_compression
// todo     | lob_partition_storage
    ;

hash_partitions_by_quantity:
    "partitions" numeric_literal ("store" "in" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?
    (table_compression)? ("overflow" "store" "in" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?
    ;

enable_disable_clause:
    "enable"|"disable"
    ;

alter_table_options:
    enable_disable_clause ("validate"|"novalidate")?
    (
        ("unique" (OPEN_PAREN! identifier2 (COMMA! identifier2) CLOSE_PAREN!))
        | ("primary" "key")
        | ("constraint" identifier2)
    )
    ;

organization_spec:
    "organization"!
        ( ("index"! ("including" identifier2)? ("pctthreshold" numeric_literal)?)
            {  __markRule(IOT_TYPE);}
        | "heap"!
            {  __markRule(HEAP_ORGINIZED);}
        | ("external") => external_table_spec
            {  __markRule(EXTERNAL_TYPE);}
        )
    ;

parallel_clause:
    ("parallel"
        (
            ( OPEN_PAREN
                "degree" (numeric_literal | "default")
                "instances" (numeric_literal | "default")
                CLOSE_PAREN )
            | numeric_literal
        )?
    )
    | "noparallel"
    ;

// REJECT LIMIT UNLIMITED
reject_spec:
    "reject" "limit" ("unlimited"| NUMBER)
    ;

storage_spec:
    "storage" OPEN_PAREN (storage_params)+ CLOSE_PAREN
    ;

storage_params:
    ("initial" (STORAGE_SIZE|numeric_literal))
    | ("next" (STORAGE_SIZE|numeric_literal))
    | ("minextents" numeric_literal)
    | ("maxextents" (numeric_literal|"unlimited"))
    | ("pctincrease" numeric_literal)
    | ("freelists" numeric_literal)
    | ("freelist" "groups" numeric_literal)
    | ("optimal" (STORAGE_SIZE|"null")?)
    | ("buffer_pool" ( "keep" | "recycle" | "default"))
    | "encrypt"
    ;

/*
partition_spec:
    ( ("partition" "by" "range" OPEN_PAREN identifier2 CLOSE_PAREN)
     /// | "local"
    )
    OPEN_PAREN partition_item (COMMA partition_item)* CLOSE_PAREN
    ;

tablespace_params:
    ("tablespace" identifier)
    | ("pctused" numeric_literal)
    | ("pctfree" numeric_literal)
    | ("initrans" numeric_literal)
    | ("maxtrans" numeric_literal)
    | storage_spec
    ;

*/

constaraint:
    "constraint"! constraint_name (
        pk_spec_constr
        | fk_spec_constr
        | ("check" condition)
        | unique_contsr
     )
    {  __markRule(CONSTRAINT);}
    ;

pk_spec_constr:
    "primary"! "key"! OPEN_PAREN! column_name_ddl_list CLOSE_PAREN!
    {  __markRule(PK_SPEC);}
    ;

fk_spec_constr:
    "foreign"! "key"! OPEN_PAREN! column_name_ddl_list CLOSE_PAREN!
    "references"! table_name_ddl OPEN_PAREN! column_name_ddl_list CLOSE_PAREN!
    ("disable")? ("on" "update" referential_actions)? ("on" "delete" referential_actions)?
    {  __markRule(FK_SPEC);}
    ;

unique_contsr:
    "unique" OPEN_PAREN column_name_ddl (COMMA! column_name_ddl)* CLOSE_PAREN
    {  __markRule(UNIQUE_CONSTRAINT);}
    ;

column_name_ddl_list:
    column_name_ddl (COMMA! column_name_ddl)*
    {  __markRule(COLUMN_NAME_LIST);}
    ;

referential_actions:
    "cascade"|"restrict"|("no" "action")|("set" "null")|("set" "default")
    ;

constraint_name:
    identifier
    {  __markRule(CONSTRAINT_NAME);}
    ;
// -------------------------------------------------------------------
// [CREATE TABLE END] ------------------------------------------------
// -------------------------------------------------------------------


// -------------------------------------------------------------------
// [ALTER TABLE START] ------------------------------------------------
// -------------------------------------------------------------------
alter_table:
    "alter"! "table"! table_name_ddl (constraint_clause)? (alter_table_options)?
    {  __markRule(ALTER_TABLE);}
    ;

constraint_clause:
    ("add" (add_syntax_1 | (OPEN_PAREN! add_syntax_2 CLOSE_PAREN!) ) )
    | ("modify" (modify_constraint_clause | (OPEN_PAREN! modify_syntax_2 CLOSE_PAREN!) | modify_syntax_1) )
    | ("rename"
            (   ("constraint" identifier2 "to" identifier2)
                | ("to" identifier2)
                | ("column" identifier2 "to" identifier2) )  
      )
    | "drop" ( ("column" identifier2) | drop_clause )
    ;

modify_constraint_clause:
    ("constraint" identifier ("rely")? ("disable"|"enable")? ("validate"|"novalidate")?)
    | ("primary" "key" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
    | ("unique" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
    ;

add_syntax_1:
    column_def_alter
    | inline_out_of_line_constraint
    ;

add_syntax_2:
    (column_def_alter) => (column_def_alter (COMMA! column_def_alter)*)
    | inline_out_of_line_constraint
    ;

modify_syntax_1:
    column_def_alter
    ;

modify_syntax_2:
    column_def_alter (COMMA! column_def_alter)*
    ;

column_def_alter:
    column_name_ddl (datatype)? ( not_null|("default" ("sysdate"|numeric_literal)) )? (pk_spec|fk_spec|column_constraint)?
    ;

constraint:
    inline_out_of_line_constraint
// todo    inline_constraint
// todo    | out_of_line_constraint
// todo    | inline_ref_constraint
// todo    | out_of_line_ref_constraint
    ;
/*
   ADD CONSTRAINT XDV_TAM_1HR_DDE_FK FOREIGN KEY (DDE_ID)
      REFERENCES XDV_DTM_HRDATE_DT (ID)
       RELY DISABLE NOVALIDATE;
*/

inline_out_of_line_constraint:
    ("constraint" identifier)? (
        not_null
        | ("unique" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
        | ("primary" "key" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?) // ("rely" "using" "index" identifier2)? ("enable")? )
        | ("foreign" "key"
                (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
                "references" identifier2 (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
                ("rely")? ("disable"|"enable")? ("validate"|"novalidate")? ("on" "delete" ("cascade"|("set" "null")) )?
            )
        | ("check" condition)
    )
    (using_index_clause)?
    ;

drop_clause:
    ("primary" "key" ("cascade")? (("keep"|"drop") "index")? )
    | ("unique" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN ("cascade")? (("keep"|"drop") "index")?)
    | ("constraint" identifier ("cascade")?)
    ;

enable_disable_clause2:
    using_index_clause ("cascade")? (("keep"|"drop") "index")?
    ;

using_index_clause:
    ("rely")? "using" "index" (
        ((schema_name DOT)? identifier2)
// todo        | (OPEN_PAREN create_index_statement CLOSE_PAREN)
        | index_properties
    )  ("enable")?
    ;

index_properties:
    ((index_attributes)+)
    | global_partitioned_index
    | local_partitioned_index
    ;

index_attributes:
    ("tablespace" (identifier2|"default"))
    | physical_attributes_clause
    | logging_clause
    | "online"
    | ("sort"|"nosort")
    | "reverse"
    | ("visible"|"novisible")
    | parallel_clause
    ;

global_partitioned_index:
    "global" "partition" "by" (
        ("range" OPEN_PAREN index_column_spec_list CLOSE_PAREN OPEN_PAREN  index_partitioning_clause CLOSE_PAREN)
        | ("hash" OPEN_PAREN index_column_spec_list CLOSE_PAREN (individual_hash_partitions | hash_partitions_by_quantity))
    )
    ;

index_partitioning_clause:
    "partition" (identifier2)? "values" "less" "than"
            OPEN_PAREN numeric_literal (COMMA! numeric_literal)* CLOSE_PAREN segment_attributes_clause 
    ;

local_partitioned_index:
    "local" (
        on_range_partitioned_table
//        | on_list_partitioned_table
//        | on_hash_partitioned_table
//        | on_comp_partitioned_table
    )?
    ;

on_range_partitioned_table:
    OPEN_PAREN local_partition_item (COMMA! local_partition_item)* CLOSE_PAREN
    ;

local_partition_item:
     "partition" (identifier2)? (segment_attributes_clause|table_compression)*
     ;

// -------------------------------------------------------------------
// [ALTER TABLE END] ------------------------------------------------
// -------------------------------------------------------------------


type_definition:
    "type"! type_name ("as"!|"is"!)
    (
        ("object"! OPEN_PAREN! record_item (COMMA! record_item )* CLOSE_PAREN!)
            {  __markRule(OBJECT_TYPE_DEF);}
        | ("table"! "of"! type_spec ("index"! "by"! type_spec)? ("not" "null")? )
            {  __markRule(TABLE_COLLECTION);}
        | ("record" OPEN_PAREN! record_item ( COMMA! record_item )* CLOSE_PAREN!)
            {  __markRule(RECORD_TYPE_DECL); }
        | ( "ref" "cursor"! ( "return"! record_name (PERCENTAGE "rowtype")? )? )
            {  __markRule(REF_CURSOR); }
        | ( "varray"! OPEN_PAREN! plsql_expression CLOSE_PAREN! "of"! type_spec ("not" "null")?)
            {  __markRule(VARRAY_COLLECTION); }
    )
    ;


create_view:
    "view"! view_name_ddl (OPEN_PAREN! v_column_def (COMMA! v_column_def)* CLOSE_PAREN!)?
    "as"! select_expression  ("with" (("check" "option")|("read" "only")))? (SEMI!)?
    ;

v_column_def:
    name_fragment
    {  __markRule(V_COLUMN_DEF);}
    ;

package_spec :
    "package"! (schema_name DOT!)? o:package_name (a:"authid" {#a.setType(AUTHID);} identifier)?
    (   ("wrapped" {
//                String package_name = #o.getFirstChild().getText();
                String package_name = #o.getText();
//                throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
                process_wrapped_package(package_name);
             }
        )
        | (("is"! | "as"!) (serially_reusable_pragma SEMI!)?
            ( package_obj_spec_ex)* "end"! (package_name! )? SEMI!)
    )
    ;

package_obj_spec_ex:
    package_obj_spec
    | (IF_COND_CMPL condition
            THEN_COND_CMPL cond_comp_seq (ELSE_COND_CMPL cond_comp_seq)? END_COND_CMPL
//    | (if_cond_cmpl condition
//            then_cond_cmpl cond_comp_seq (else_cond_cmpl cond_comp_seq)? end_cond_cmpl
      )
    { __markRule(CONDITIONAL_COMPILATION);}
    ;


cond_comp_seq:
    (error_cond_compliation)* (package_obj_spec)*
    { __markRule(COND_COMP_SEQ);}
    ;

error_cond_compliation:
    ERROR_COND_CMPL string_literal END_COND_CMPL
//    error_cond_cmpl string_literal end_cond_cmpl
    { __markRule(COND_COMP_ERROR);}
    ;

package_body :
    "package"! ("body"!)? (schema_name DOT!)? o:package_name ("is"! | "as"!)
    (serially_reusable_pragma SEMI!)?
    ( package_obj_body )*
    (package_init_section)? "end"! (package_name! )? SEMI!
    {  __markRule(PACKAGE_BODY);}
    ;

package_init_section:
    "begin"  seq_of_statements
    {  __markRule(PACKAGE_INIT_SECTION);}
    ;

package_obj_spec:
    variable_declaration
    | subtype_declaration
    | cursor_declaration
    | cursor_spec
    | (type_definition SEMI!)
//    | plsql_table_declaration
    | procedure_spec
    | function_spec
    | exception_declaration
    | exception_pragma
    | restrict_ref_pragma
    | interface_pragma
    | builtin_pragma
    | fipsflag_pragma
    | timestamp_pragma
    ;

/*
if_cond_cmpl:
    DOLLAR "if"
    ;

then_cond_cmpl:
    DOLLAR "then"
    ;

else_cond_cmpl:
    DOLLAR "else"
    ;

end_cond_cmpl:
    DOLLAR "end"
    ;

error_cond_cmpl:
    DOLLAR "error"
    ;
*/

condition_compilation:
    IF_COND_CMPL condition THEN_COND_CMPL cond_comp_seq2 (ELSE_COND_CMPL cond_comp_seq2)? END_COND_CMPL
//    if_cond_cmpl condition then_cond_cmpl cond_comp_seq2 (else_cond_cmpl cond_comp_seq2)? end_cond_cmpl
    { __markRule(CONDITIONAL_COMPILATION);}
    ;

cond_comp_seq2:
    (error_cond_compliation)*
    ((function_declaration ("is"|"as")) => function_body
     | (procedure_declaration ("is"|"as")) => procedure_body
     | package_obj_spec)*
    { __markRule(COND_COMP_SEQ2);}
    ;

variable_declaration :
    variable_name ("constant")?  type_spec ("not" "null")? ((ASSIGNMENT_EQ|default1) plsql_expression)? SEMI!
    { __markRule(VARIABLE_DECLARATION);}
    ;

subtype_declaration :
    "subtype" (type_name|datatype) "is"
    (
        (type_spec) => type_spec
        | (table_name PERCENTAGE! "type"! )
    )
    ("not" "null")? SEMI!
    {  __markRule(SUBTYPE_DECL); }
    ;

cursor_declaration :
    "cursor" cursor_name (OPEN_PAREN! argument_list CLOSE_PAREN!)?
     "is"! select_command SEMI!
    { __markRule(CURSOR_DECLARATION);}
    ;

package_obj_body:
    (function_declaration ("is"|"as")) => function_body
    | (procedure_declaration ("is"|"as")) => procedure_body
    | package_obj_spec
    | condition_compilation
    ;

seq_of_statements:
    (statement_tmpl)+
//    ( (statement SEMI!) | (START_LABEL label_name END_LABEL) )
//        ( (statement SEMI!) | (START_LABEL label_name END_LABEL))*
    { __markRule(STATEMENT_LIST);}
    ;


statement_tmpl:
    (statement SEMI!)
    | (START_LABEL label_name END_LABEL)
    | (IF_COND_CMPL condition THEN_COND_CMPL seq_of_statements (ELSE_COND_CMPL seq_of_statements)?  END_COND_CMPL )
//    | (if_cond_cmpl condition then_cond_cmpl seq_of_statements (else_cond_cmpl seq_of_statements)?  end_cond_cmpl )
    {  __markRule(CONDITIONAL_COMPILATION);}
    ;

statement :
        ("begin" | "declare") => begin_block   //// (("declare" declare_list)? plsql_block) /// begin_block   ///plsql_block
        | ( "loop" | "for" | "while" ) => loop_statement
          { __markRule(LOOP_STATEMENT);}
        | ( "forall" ) => forall_loop
          { __markRule(LOOP_STATEMENT);}
        | ("if" ) => if_statement
        | ( "goto" ) => goto_statement
          { __markRule(GOTO_STATEMENT);}
        | ( "raise" )=> raise_statement
          { __markRule(RAISE_STATEMENT);}
        | ( "exit" ) => exit_statement
          { __markRule(EXIT_STATEMENT);}
        | null_statement
        | ( "return" ) => return_statement
          { __markRule(RETURN_STATEMENT);}
        | ( "case" ) => case_statement
          { __markRule(CASE_STATEMENT);}
        | ( "select" | "update" | "insert" | "delete" |                 /// "with" | ("alter" ("system" | "session") ) |
            "grant" | "commit" |                                // | "lock" | "execute" | "open" | "fetch" | "close" "set" |
            "rollback" | "merge") => sql_statement
        | ("execute") => immediate_command
        | ("lock") => lock_table_statement
        | ("open") => open_statement
        | ("close") => close_statement
        | ("fetch") => fetch_statement
        | set_transaction_command
        | savepoint_statement
        | alter_command
        | ( plsql_lvalue ASSIGNMENT_EQ) => assignment_statement
        | ( procedure_call ( DOT procedure_call )*)
    ;


sql_percentage:
    "sql" PERCENTAGE (
        "found"
            { __markRule(LAST_STMT_RESULT_BOOL);}
        | "notfound"
            { __markRule(LAST_STMT_RESULT_BOOL);}
        | "rowcount"
            { __markRule(LAST_STMT_RESULT_NUM);}
        | "isopen"
            { __markRule(LAST_STMT_RESULT_BOOL);}
        | ("bulk_rowcount" OPEN_PAREN plsql_expression CLOSE_PAREN )
            { __markRule(LAST_STMT_RESULT_NUM);}
        | "bulk_exceptions" (
           (OPEN_PAREN plsql_expression CLOSE_PAREN DOT ("error_index" | "error_code"))
            | ((DOT) "count")
          )
            { __markRule(LAST_STMT_RESULT_NUM);}
     )
    ;

case_statement:
    "case"! (
        // simple case_statement
        ( plsql_expression ("when" plsql_expression "then" seq_of_statements)+ ) => plsql_expression ("when" plsql_expression "then" seq_of_statements)+
        // searched case_statement
        | ( "when" condition t:"then" seq_of_statements )+ )
    ( e:"else" seq_of_statements ) ?
    "end"! "case"!
    { __markRule(CASE_STATEMENT);}
    ;


declare_spec:
        (variable_declaration
        | subtype_declaration
        | cursor_declaration
        | exception_declaration
        | exception_pragma
        | (type_definition SEMI!)
        | pragma
//        | plsql_table_declaration
        |( (function_declaration) => function_body
           | function_spec )
        |( (procedure_declaration) => procedure_body
           | procedure_spec)
        )
        ;

pragma:
    "pragma"! "autonomous_transaction" SEMI!
    { __markRule(PRAGMA);}
    ;

assignment_statement :
    plsql_lvalue p:ASSIGNMENT_EQ! {#p.setType(ASSIGNMENT_OP);} condition
    { __markRule(ASSIGNMENT_STATEMENT);}
    ;

rvalue :
    (("prior")? ( name_fragment DOT )* name_fragment ~OPEN_PAREN ) => (("prior"!)? ( name_fragment DOT! )* name_fragment)
        { __markRule(VAR_REF);} /// !!!!!! actual type needs to be resolved

//    | (COLON_NEW|COLON_OLD) => (COLON_NEW|COLON_OLD) DOT! name_fragment
    | (COLON ("new"|"old") DOT) => (COLON ("new"|"old") DOT! name_fragment)
        { __markRule(TRIGGER_COLUMN_REF);}

    | ( function_call ( d:DOT! ((function_call) => function_call | c_record_item_ref ) )* )
     {
        if(#d != null){
             __markRule(COLLECTION_METHOD_CALL);
        }
     }
    ;

c_record_item_ref:
    identifier2
    {  __markRule(C_RECORD_ITEM_REF); }
    ;

plsql_lvalue :
    (( name_fragment DOT )* name_fragment ~OPEN_PAREN ) => ( name_fragment DOT! )* name_fragment
        { __markRule(PLSQL_VAR_REF);}
//    | (COLON_NEW|COLON_OLD) => (COLON_NEW|COLON_OLD) DOT! name_fragment
    | (COLON ("new"|"old") DOT) => (COLON ("new"|"old") DOT! name_fragment)
        { __markRule(TRIGGER_COLUMN_REF);}

    | ( function_call ( d:DOT! ((function_call) => function_call | c_record_item_ref ) )* )
     {
        if(#d != null){
             __markRule(COLLECTION_METHOD_CALL);
        }
     }
    | host_variable
    ;

collection_method:
    identifier2
    {  __markRule(COLLECTION_METHOD_NAME); }
    ;

field_name :
    identifier
    {  __markRule(FIELD_NAME); }
    ;

host_variable:
    COLON identifier2
    ;

goto_statement :
    g:"goto" label_name
    ;

label_name :
    identifier
    {  __markRule(LABEL_NAME); }
    ;

exit_statement:
    e:"exit" (label_name)? (w:"when" condition)?
    ;

datatype :
    (
        "binary_integer"
        | "natural"
        | "positive"
        | ("number"(OPEN_PAREN! (NUMBER|ASTERISK) (COMMA! NUMBER)? CLOSE_PAREN! )? )
        | ("char" (OPEN_PAREN! NUMBER ("byte")? CLOSE_PAREN! )? )
        | ("long" ("raw")?)
        | "raw" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "boolean"
        | "date"
        | "timestamp" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
            (("with" "local" "time" "zone") => ("with" "local" "time" "zone")
            | ("with" "time" "zone"))?

        | "interval"
            ( ( "year" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "month")
            | ("day" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "second" (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)
            )
        | "smallint"
        | "real"
        | "numeric" (OPEN_PAREN! NUMBER (COMMA! NUMBER)? CLOSE_PAREN! )?
        | "int"
        | "integer" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "pls_integer"
        | "double" "precision"
        | "float" ( OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "decimal"
        | "varchar" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
        | "varchar2" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
        | "nvarchar" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
        | "nvarchar2" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
        | "character" ( OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "mlslabel"
        | "blob"
        | "clob"
    )
    {  __markRule(DATATYPE);}
    ;
	exception catch [RecognitionException ex] {
	    throw ex;
	}

type_spec :
    ( datatype
    | (percentage_type) => percentage_type
    | type_name_ref )
//    {  __markRule(TYPE_SPEC);}
    ;

/* ---------- docs -----------
%TYPE and %ROWTYPE are used to define variables in PL/SQL as it is defined within the database.
The %TYPE and %ROWTYPE constructs provide data independence, reduces maintenance costs, and
allows programs to adapt as the database changes to meet new business needs.

%TYPE is used to declare a field with the same type as that of a specified table's column. Example:

    DECLARE
    v_EmpName emp.ename%TYPE;
    BEGIN
    SELECT ename INTO v_EmpName FROM emp WHERE ROWNUM = 1;
    DBMS_OUTPUT.PUT_LINE('Name = ' || v_EmpName);
    END;
    /

%ROWTYPE is used to declare a record with the same types as found in the specified database table, view or cursor. Examples:

    DECLARE
    v_emp emp%ROWTYPE;
    BEGIN
    v_emp.empno := 10;
    v_emp.ename := 'XXXXXXX';
    END;
    /

    DECLARE
    v_EmpRecord emp%ROWTYPE;
    BEGIN
    SELECT * INTO v_EmpRecord FROM emp WHERE ROWNUM = 1;
    DBMS_OUTPUT.PUT_LINE('Name = ' || v_EmpRecord.ename);
    DBMS_OUTPUT.PUT_LINE('Salary = ' || v_EmpRecord.sal);
    END;
    /
 ---------- docs ----------- */

percentage_type:
    // declaration of the variable that is based on table column with the %TYPE attribute.
    table_name DOT! column_name_ref PERCENTAGE! "type"! /// => table_name DOT! column_name_ref PERCENTAGE! "type"!
    {  __markRule(COLUMN_TYPE_REF);}

    | table_name PERCENTAGE! "rowtype"!
    {  __markRule(TABLE_TYPE_REF);}
    ;

type_name_ref :
     name_fragment (DOT! name_fragment )*
    {  __markRule(TYPE_NAME_REF);}
    ;

name_fragment :
     identifier2
    {  __markRule(NAME_FRAGMENT);}
    ;

name_fragment_ex :
     identifier3
    {  __markRule(NAME_FRAGMENT);}
    ;

identifier3:
    identifier2
    | "prior"
    | "start"
    ;

identifier4:
    identifier2
    | "prior"
    | "start"
    | "create"
    ;

type_name :
     identifier2
    {  __markRule(TYPE_NAME);}
    ;

parameter_spec :
    parameter_name ("in" )? ("out")? ("nocopy")? ("ref")?
    (type_spec)  (character_set)? ( (default1 | ASSIGNMENT_EQ) plsql_expression )?
    {  __markRule(PARAMETER_SPEC);}
    ;

character_set:
    "character" "set" ( "any_cs" | (identifier2 PERCENTAGE "charset") )
    {  __markRule(CHARACTER_SET);}
    ;

default1:
    "default"
    {  __markRule(DEFAULT);}
    ;

parameter_name :
    identifier2
    {  __markRule(PARAMETER_NAME);}
    ;

cursor_spec :
        c:"cursor" cursor_name
        (o:OPEN_PAREN!
(options {
    greedy = true;
} :
      parameter_spec
        (COMMA parameter_spec)* ) CLOSE_PAREN!)?
        "return" return_type SEMI!
        ;

procedure_spec :
    procedure_declaration SEMI!
    { __markRule(PROCEDURE_SPEC);}
    ;

function_spec :
    function_declaration SEMI!
    { __markRule(FUNCTION_SPEC);}
    ;

exception_declaration :
    exception_name "exception"! SEMI!
    { __markRule(EXCEPTION_DECL);}
    ;

exception_name :
    exception_package_name (DOT! identifier)?
    ;

exception_package_name:
    identifier
    ;

serially_reusable_pragma:
    "pragma" "serially_reusable"
    { __markRule(SERIALLY_REUSABLE_PRAGMA);}
    ;

exception_pragma :
    "pragma"! "exception_init"! OPEN_PAREN! complex_name COMMA! plsql_expression CLOSE_PAREN! SEMI!
    { __markRule(EXCEPTION_PRAGMA);}
    ;

restrict_ref_pragma :
    "pragma"! "restrict_references"! OPEN_PAREN! identifier3 (COMMA! identifier3)+ CLOSE_PAREN! SEMI!
    { __markRule(RESTRICT_REF_PRAGMA);}
    ;

interface_pragma:
    "pragma"! "interface"! OPEN_PAREN! identifier3 (COMMA! identifier3)+ CLOSE_PAREN! SEMI!
    { __markRule(INTERFACE_PRAGMA);}
    ;

builtin_pragma:
    "pragma"! "builtin"! OPEN_PAREN! string_literal (COMMA! plsql_expression)+ CLOSE_PAREN! SEMI!
    { __markRule(BUILTIN_PRAGMA);}
    ;

fipsflag_pragma:
    "pragma"! "fipsflag"! OPEN_PAREN! string_literal (COMMA! plsql_expression)+ CLOSE_PAREN! SEMI!
    { __markRule(FIPSFLAG_PRAGMA);}
    ;

timestamp_pragma:
    "pragma"! "timestamp"! OPEN_PAREN! string_literal CLOSE_PAREN! SEMI!
    { __markRule(TIMESTAMPG_PRAGMA);}
    ;

numeric_literal :
    NUMBER
    {  __markRule(NUMERIC_LITERAL);}
    ;

oracle_err_number:
        (p:PLUS | m:MINUS) ? n:NUMBER
        ;

record_item:
    record_item_name type_spec ("not" "null")? ((default1 |p:ASSIGNMENT_EQ {#p.setType(ASSIGNMENT_OP);}) plsql_expression)?
    {  __markRule(RECORD_ITEM); }
    ;

record_item_name:
    identifier2
    {  __markRule(RECORD_ITEM_NAME); }
    ;

procedure_declaration :
    "procedure"! object_name
    (options { greedy = true; } :
        ( OPEN_PAREN! argument_list CLOSE_PAREN! )?  ("as" "language" "java" "name" string_literal)?
    )
    ;

procedure_body  :
    procedure_declaration
    ( i:"is"! | a:"as"! )
    func_proc_statements
    {  __markRule(PROCEDURE_BODY); }
    ;

func_proc_statements
    : (declare_list)? plsql_block SEMI!
    {  __markRule(PLSQL_BLOCK);}
    ;

begin_block
    : ("declare" (declare_list)?)? plsql_block
    {  __markRule(PLSQL_BLOCK);}
//    { #.begin_block = #.([BEGIN_BLOCK, "BEGIN_BLOCK" ], #.begin_block); }
    ;

plsql_block :
    "begin"!
    (seq_of_statements )?
    ( exception_section )?
    "end"! ( identifier2 ) ?
//    { #.plsql_block = #.([PLSQL_BLOCK, "PLSQL_BLOCK" ], #.plsql_block);}
    ;

exception_section:
    "exception"! (exception_handler)+
    {  __markRule(EXCEPTION_SECTION);}
    ;

declare_list:
    (declare_spec)+
    { __markRule(DECLARE_LIST);}
    ;

exception_handler :
    w:"when" exception_name
    (o:"or" exception_name )* t:"then"
    seq_of_statements
    ;

function_declaration :
    "function"! object_name
     (options { greedy = true; } :
        ( OPEN_PAREN! argument_list CLOSE_PAREN!
        )?
    )
    "return"! return_type (character_set)? ("pipelined")? ("parallel_enable")? ("using" identifier2)? ("deterministic")?
    ("as" "language" "java" "name" string_literal)?
    ;

function_body  :
    function_declaration
        ( (( "is"! | "as"! ) func_proc_statements)
            {  __markRule(FUNCTION_BODY); }
          | ("aggregate" "using" identifier2)
            {  __markRule(CUSTOM_AGGR_FUNCTION); }
        )
    ;

argument_list:
    // argument ( COMMA! argument )*
    parameter_spec ( COMMA! parameter_spec )*
    {  __markRule(ARGUMENT_LIST); }
    ;


object_name :
    identifier3 (DOT! identifier2 )?
    {  __markRule(OBJECT_NAME); }
    ;

return_type :
    type_spec
    {  __markRule(RETURN_TYPE); }
    ;

/*
    1. a regular function call
    2. collection initialization
*/
function_call:
    callable_name_ref OPEN_PAREN! (call_argument_list )?  CLOSE_PAREN!
    {  __markRule(FUNCTION_CALL); }
    ;

procedure_call:
    callable_name_ref (OPEN_PAREN! (call_argument_list)? CLOSE_PAREN!)?
    { __markRule(PROCEDURE_CALL);}
    ;

callable_name_ref:
//    ( name_prefix DOT! )* exec_name_ref
//    name_fragment ( DOT! name_fragment )*
    name_fragment_ex ( DOT! name_fragment_ex )*
    { __markRule(CALLABLE_NAME_REF);}
    ;

variable_name :
    identifier2
    {  __markRule(VARIABLE_NAME); }
    ;

null_statement:
    "null"
    { __markRule(NULL_STATEMENT);}
    ;

raise_statement :
    "raise"! ( exception_name )?
    ;

return_statement :
    "return"! (condition)? // ( plsql_expression)?
    ;

forall_loop:
    forall_header ("save" "exceptions")?
	statement
    ;

forall_header:
    "forall" loop_index "in" (
        (forall_boundary DOUBLEDOT forall_boundary)
        | ("values" "of" plsql_lvalue )
        | ("indices" "of" plsql_lvalue )
    )
    { __markRule(FORALL_LOOP_SPEC);}
    ;

forall_boundary:
    numeric_literal
    | plsql_lvalue
    ;

loop_statement :
    (label_name! )?
    (   ("while" condition)
        |   ("for" (
                (numeric_loop_spec) => numeric_loop_spec
                | cursor_loop_spec
                )
            )
    )? "loop"
    seq_of_statements
    "end"! "loop"! (label_name!)?
    ;

numeric_loop_spec :
    loop_index "in"! ("reverse"!)? plsql_expression DOUBLEDOT! plsql_expression
    { __markRule(NUMERIC_LOOP_SPEC);}
    ;


//coll_for:
//    loop_index "in"! ("reverse")? complex_name DOUBLEDOT! complex_name
//    ;
//
// for cus in ( select ID, UPD_CNT from XSL_CUS_CUSTOMER_T where PARENT_ID = i_id and FREEZE_DATE is null for update )
// loop
//  	  deleteCustomer( cus.ID, cus.UPD_CNT, i_deleter_user_id, aud_action_type, aud_action_source );
// end loop;

cursor_loop_spec :
    loop_index "in"! (
        ( cursor_name (OPEN_PAREN! call_argument_list CLOSE_PAREN!)?)
//        ( cursor_name (parentesized_plsql_exp_list )?)
            { __markRule(CURSOR_REF_LOOP_SPEC);}
        | (OPEN_PAREN! select_expression CLOSE_PAREN!)
            { __markRule(CURSOR_IMPL_LOOP_SPEC);}
    )
    ;

loop_index:
    identifier2
    { __markRule(LOOP_INDEX);}
    ;

complex_name:
//    (identifier2 DOT!)* identifier2
    identifier (DOT! identifier2)*
    {  __markRule(COMPLEX_NAME); }
    ;

boolean_literal :
    ("true" | "false")
    {  __markRule(BOOLEAN_LITERAL); }
    ;

index_name:
    identifier
    {  __markRule(INDEX_NAME); }
    ;

integer_expr :
    plsql_expression
    | type_spec
    ;

cursor_name :
    identifier
    {  __markRule(CURSOR_NAME); }
    ;

record_name:
    identifier
    ;

num_expression:
    num_term ((p:PLUS {#p.setType(PLUS_OP);} | m:MINUS {#m.setType(MINUS_OP);} ) num_term )*
    {
        if(#p != null || #m != null){
             __markRule(ARITHMETIC_EXPR);
        }
    }
    ;

num_term:
    num_factor (( as:ASTERISK {#as.setType(MULTIPLICATION_OP);} | dv:DIVIDE {#dv.setType(DIVIDE_OP);} ) num_factor )*
    {
        if(#as != null || #dv != null){
             __markRule(ARITHMETIC_EXPR);
        }
    }
    ;

num_factor:
    may_be_negate_base_expr ("**" integer_expr)?
        (d:"day" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "second" (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)?
    {

        if(#d != null){
             __markRule(INTERVAL_DAY_TO_SEC_EXPR);
        }
    }
    ;

may_be_negate_base_expr:
    (s1:sign!)? may_be_at_time_zone
    {
        if(#s1 != null){
             __markRule(ARITHMETIC_EXPR);
        }
    }
    ;

may_be_at_time_zone:
    base_expression (at:"at"! "time"! "zone"! timezone_spec)?
    {
        if(#at != null){
             __markRule(AT_TIME_ZONE_EXPR);
        }
    }
    ;

sign:
    p:PLUS {#p.setType(PLUS_OP);}
    | m:MINUS {#m.setType(MINUS_OP);}
    ;

plsql_expr_list:
    plsql_expression (c:COMMA! plsql_expression )*
    { __markRule(EXPR_LIST);}
/*
    {
        if(#c != null)
            {#.plsql_expr_list = #.([PLSQL_EXPR_LIST, "PLSQL_EXPR_LIST" ], #.plsql_expr_list);}
    }
*/
    ;

parentesized_plsql_exp_list:
    OPEN_PAREN! plsql_expr_list cp:CLOSE_PAREN!
//    { #.parentesized_plsql_exp_list = #.([PAREN_EXPR_LIST, "PAREN_EXPR_LIST" ], #.parentesized_plsql_exp_list); }
    ;

condition:
    logical_term ( p:"or" {#p.setType(OR_LOGICAL);} logical_term )*
    {
        if(#p != null){
             __markRule(LOGICAL_EXPR);
        }
    }
    ;

logical_term:
    maybe_neg_factor  (p:"and" {#p.setType(AND_LOGICAL);} maybe_neg_factor )*
    {
        if(#p != null){
             __markRule(LOGICAL_EXPR);
        }
    }
    ;

maybe_neg_factor:
    (p:"not"! {#p.setType(NOT_LOGICAL);})? plsql_expression33
    {
        if(#p != null){
             __markRule(LOGICAL_EXPR);
        }
    }
    ;

/* MEMBER OF info
Oracle Database 10g allows you to use MEMBER OF syntax to determine if a particular values
is a "member of" a nested table. Here is an example:

DECLARE
 TYPE clientele IS TABLE OF VARCHAR2 (64);
 client_list_12 clientele :=
 clientele ('Customer 1', 'Customer 2');
BEGIN
 IF 'Customer 1' MEMBER OF client_list_12
 THEN
 DBMS_OUTPUT.put_line ('Customer 1 is in the 12 list');
 END IF;

 IF 'Customer 3' NOT MEMBER OF client_list_12
 THEN
 DBMS_OUTPUT.put_line ('Customer 3 is not in the 12 list');
 END IF;
END;
/
*/

plsql_expression33:
    ( "current" "of" ) => ( "current"! "of"! ) identifier
        {  __markRule(CURRENT_OF_CLAUSE); }
    | ( "exists" ) => "exists"! subquery
        {  __markRule(EXISTS_EXPR); }
    | plsql_expression (
        (relational_op) => relational_op plsql_expression
        {  __markRule(RELATION_CONDITION); }
        | (("not" )? "in" ) => ("not")? "in"! exp_set
        {  __markRule(IN_CONDITION); }
        | (( "not" )? "like" ) => ( "not" )? "like" plsql_expression ( "escape" (string_literal|identifier2) )?
        {  __markRule(LIKE_CONDITION); }
        | (( "not" )? "between") => ( "not" )? "between"! plsql_expression "and"! plsql_expression
        {  __markRule(BETWEEN_CONDITION); }
        | ("is" ( "not" )? "null" )
        {  __markRule(ISNULL_CONDITION); }
        | ( ("not")? "member" "of") =>  (("not"!)? "member"! "of"! (name_fragment DOT!)? name_fragment )
        {  __markRule(MEMBER_OF); }
        )?
    | (OPEN_PAREN! plsql_expr_list CLOSE_PAREN! (EQ | NOT_EQ) subquery) => OPEN_PAREN! plsql_expr_list CLOSE_PAREN! (EQ | NOT_EQ) subquery
        {  __markRule(SUBQUERY_CONDITION); }
    ;


plsql_expression:
    num_expression (c:CONCAT {#c.setType(CONCAT_OP);} num_expression )*
    {
        if(#c != null ){
             __markRule(ARITHMETIC_EXPR);
        }
    }
    ;

base_expression:
      ( "cast" ) => ( cast_proc )
      | ("sqlcode") => "sqlcode"
        {  __markRule(SQLCODE_SYSVAR);}
      | ("sqlerrm") => ("sqlerrm" (OPEN_PAREN! base_expression CLOSE_PAREN!)? )
        {  __markRule(SQLERRM_SYSVAR);}
      | ( "trim" ) => ( trim_function )
      | ( "count" ) => ( count_function )
      | ( "case" ) => ( case_expression )
      | ( ("rank"|"dense_rank") OPEN_PAREN ) => dence_rank_analytics_func
      | ("sql" PERCENTAGE ) => sql_percentage
      | ( extract_date_function ) => extract_date_function
        // interval examples: "INTERVAL '30' MINUTE", "INTERVAL '2-6' YEAR TO MONTH", "INTERVAL '3 12:30:06.7' DAY TO SECOND(1)"
      | ("interval"! string_literal ("second" | "minute" | "hour" | "day" | "month" | "year")
                    ("to" ("second"|"month") (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)? )
        {  __markRule(INTERVAL_CONST);}
      | ("timestamp"! string_literal)
        {  __markRule(TIMESTAMP_CONST);}
      | (( "all" | "any" )) => ( a1:"all" | a2:"any" ) subquery
      | ((cursor_name | subquery) PERCENTAGE ("rowcount"|"found"|"notfound"|"isopen") ) => ident_percentage
      | ( OPEN_PAREN "select") => subquery
        {  __markRule(SUBQUERY_EXPR);}
      | OPEN_PAREN! condition CLOSE_PAREN!
        {  __markRule(PARENTHESIZED_EXPR);}
      | string_literal
//      | date_literal
      | numeric_literal
      | boolean_literal
      | null_statement
      | pseudo_column
      | (column_spec OPEN_PAREN! PLUS! CLOSE_PAREN!) => ( column_spec OPEN_PAREN! PLUS! CLOSE_PAREN! )
        { __markRule(COLUMN_OUTER_JOIN);}
      | ( identifier DOT ("nextval" | "currval")) => sequence_expr

      | rvalue
      ;

ident_percentage:
    (cursor_name | subquery) PERCENTAGE!
        ("rowcount"
            {  __markRule(ROWCOUNT_EXRESSION);}
          |"found"
            {  __markRule(CURSOR_STATE);}
          |"notfound"
            {  __markRule(CURSOR_STATE);}
          |"isopen"
            {  __markRule(CURSOR_STATE);}
        )
    ;

dence_rank_analytics_func:
    // DENSE_RANK() OVER (ORDER BY MAX(SEVERITY * 1000000000 + FAULT_TS / 1000) DESC, XTL_PDC_REGISTRATION_T.MNO_NAME)
    // DENSE_RANK() OVER (PARTITION BY deptno ORDER BY sal) "rank"
    ("rank"|"dense_rank") OPEN_PAREN CLOSE_PAREN "over" OPEN_PAREN (
            order_clause
            | ("partition" "by" plsql_expression)
        ) CLOSE_PAREN
    ;

timezone_spec:
    ( string_literal
     | complex_name // identifier (DOT identifier)*
     | "sessiontimezone"
     | "dbtimezone")
    {  __markRule(TIMEZONE_SPEC); }
    ;

sequence_expr:
    name_fragment DOT! name_fragment /// ("nextval" | "currval")
    { __markRule(SEQUENCE_EXPR);}
    ;

count_function:
    "count"! OPEN_PAREN! (
        asterisk_column
        | (ident_asterisk_column) =>  ident_asterisk_column
        | ( ("distinct")? plsql_expression )
    ) CLOSE_PAREN!
//    ASTERISK | ( ("distinct")? plsql_expression )) CLOSE_PAREN!
    { __markRule(COUNT_FUNC);}
    ;

string_literal :
    (QUOTED_STR)+
    {  __markRule(STRING_LITERAL); }
    ;

extract_date_function:
     "extract"! OPEN_PAREN! extract_consts "from"! plsql_expression CLOSE_PAREN!
    { __markRule(EXTRACT_DATE_FUNC);}
    ;

/*
extract_date_function_name:
    "extract"
    {.#.extract_date_function_name = #.([FUNCTION,_NAME, "FUNCTION,_NAME" ], #.extract_date_function_name);}
    ;
*/
extract_consts:
    "second" | "minute" | "hour" | "day" | "month" | "year" | "timezone_hour" | "timezone_minute"
    ;

date_literal:
    (QUOTED_STR)+
    ;

commit_statement:
    "commit" ( "work" )?
    { __markRule(COMMIT_STATEMENT);}
    ;

case_expression:
    ("case"! (
        // searched_case_expression
        ( "when" condition t:"then" plsql_expression )+
        // simple_case_expression
        | smpl:plsql_expression ("when" plsql_expression "then" plsql_expression)+
     )
    ( "else" plsql_expression ) ?
    "end"!)
    {
        if(#smpl != null){
            { __markRule(CASE_EXPRESSION_SMPL);}
        } else {
            { __markRule(CASE_EXPRESSION_SRCH);}
        }
    }
    ;


if_statement:
    "if"! condition
    "then"! seq_of_statements
    (elsif_statements)*
    (else_statements)?
    "end"! "if"!
    {  __markRule(IF_STATEMENT); }
    ;

elsif_statements:
    "elsif"! condition "then"! seq_of_statements
    {  __markRule(ELSIF_STATEMENT); }
    ;

else_statements:
    "else"! seq_of_statements
    {  __markRule(ELSE_STATEMENT); }
    ;

sql_statement:
        select_command
        | insert_command
        | update_command
        | delete_command
        | merge_command

        | commit_statement
        | rollback_statement
    ;


select_command:
    ( select_expression | subquery )
//    { #.select_command = #.([SELECT_COMMAND, "SELECT_COMMAND" ], #.select_command); }
    ;

subquery:
    o:OPEN_PAREN! select_command cp:CLOSE_PAREN!
    {  __markRule(SUBQUERY); }
    ;

select_expression :
    select_first ( (
        ( "union" ("all")? )
        | "intersect"
        | "minus"
      ) sub:select_first ) *
    {
        if(#sub != null){
            {  __markRule(SELECT_EXPRESSION_UNION); }
        }
    }
    ;


/*
 * There are ambiguity issues here; it seems to be legal to put any number
 * of brackets round a select statement, but then the thing reports an
 * unexpected token when the MINUS turns up.
 */

select_first:
    ( select_up_to_list
        ( into_clause ) ?     // the only difference
        table_reference_list_from
        ( where_condition )?
        ( connect_clause )?
        ( group_clause )?
        ( order_clause )?
        ( update_clause )?
     )
     {  __markRule(SELECT_EXPRESSION); }
    | o:OPEN_PAREN select_first c:CLOSE_PAREN
    ;



/* Example of formatting markup

select_subsequent:
    select_up_to_list
        { handle_ws(_NEWLINE_WS_); } table_reference_list_from
        ( { handle_ws(_NEWLINE_WS_); } where_condition )?
        ( { handle_ws(_NEWLINE_WS_); } connect_clause )?
        ( { handle_ws(_NEWLINE_WS_); } group_clause )?
    | o:OPEN_PAREN select_subsequent c:CLOSE_PAREN
    ;


into_clause:
    ("bulk" { handle_ws(_KEEP_WS_); } "collect")? i:"into"! lvalue_list  ///plsql_exp_list
    {  __markRule(INTO_CLAUSE);}
    ;
*/


into_clause:
    ("bulk" "collect")? i:"into"! plsql_lvalue_list  ///plsql_exp_list
    {  __markRule(INTO_CLAUSE);}
    ;

plsql_lvalue_list:
    plsql_lvalue (COMMA! plsql_lvalue)*
    ;

select_up_to_list:
//(options { greedy = true; } :
/*
    (w:"with" alias  a1:"as" subquery
         (c:COMMA alias a2:"as" subquery )*
    )?
*/
    "select"! ( "all" | "distinct" | "unique")?
    select_list
//)
    ;


select_list:
(options { greedy = true; } :
        displayed_column ( COMMA! displayed_column )*
    )
    ;

displayed_column:
    asterisk_column
    | (ident_asterisk_column) =>  ident_asterisk_column
    | expr_column
    ;

asterisk_column:
    ASTERISK
    {  __markRule(ASTERISK_COLUMN); }
    ;

ident_asterisk_column:
//    table_ref DOT ASTERISK
    name_fragment DOT ASTERISK 
    {  __markRule(IDENT_ASTERISK_COLUMN); }
    ;

expr_column:
    plsql_expression ( alias )?
    {  __markRule(EXPR_COLUMN); }
    ;


immediate_command:
    "execute"! "immediate"!
                (   (plsql_lvalue) => plsql_lvalue
                    |   plsql_expression
                )
        ( ("bulk" "collect")? "into" plsql_lvalue_list ) ?
//        ( ("bulk" "collect")? "into" plsql_expr_list ) ?
        ( "using" plsql_exp_list_using ) ?
    {  __markRule(IMMEDIATE_COMMAND);}
    ;


plsql_exp_list_using:
    ( ("in"|"out")? plsql_expression COMMA ) => ("in"|"out")? plsql_expression (COMMA! ("in"|"out")? plsql_expression )*
    { __markRule(PLSQL_EXPR_LIST);}
    | ("in"|"out")? plsql_expression
    ;

alter_command:
    alter_system_session
    | alter_table
    | alter_trigger
    ;

alter_system_session:
    "alter" ( "system" | "session" )
    (
        ( "flush" "shared_pool" )
        | (
            ( "set"  identifier EQ
                ( string_literal | numeric_literal | identifier )
            )
            | ( "reset" identifier )
          )
         ( "sid" EQ ( string_literal | ASTERISK ) ) ?
    )
    ;


table_reference_list_from:
        "from"! selected_table ("partition"! OPEN_PAREN! identifier2 CLOSE_PAREN!)?
        (
         ( "left" | "right" | "inner" | "outer" | "join") => ansi_spec
         | (COMMA! selected_table ("partition"! OPEN_PAREN! identifier2 CLOSE_PAREN!)?)
        )*
{
     __markRule(TABLE_REFERENCE_LIST_FROM);
}
            ;

table_reference_list:
    selected_table ( COMMA! selected_table )*
    { __markRule(TABLE_REFERENCE_LIST);}
    ;

where_condition:
    "where"! condition
    {  __markRule(WHERE_CONDITION); }
    ;


call_argument_list:
    call_argument (COMMA! call_argument)*
    {  __markRule(CALL_ARGUMENT_LIST);}
    ;

call_argument :
    (parameter_name_ref PASS_BY_NAME!)? condition   ///plsql_expression
    {  __markRule(CALL_ARGUMENT);}
    ;

parameter_name_ref:
    name_fragment
    { __markRule(PARAMETER_REF);}
    ;

schema_name:
    identifier2
    { __markRule(SCHEMA_NAME);}
    ;

view_name_ddl :
    identifier
    { __markRule(VIEW_NAME_DDL);}
    ;

table_name_ddl :
    identifier2
    { __markRule(TABLE_NAME_DDL);}
    ;

table_name :
    identifier2
    { __markRule(TABLE_NAME);}
    ;

table_ref :
    identifier
    { __markRule(TABLE_REF);}
    ;

alias :
    ( "as"! )?  alias_ident
    { __markRule(ALIAS_NAME);}
    ;

alias_ident:
    identifier2
    { __markRule(ALIAS_IDENT);}
    ;

package_name:
    identifier
    { __markRule(PACKAGE_NAME);}
    ;

column_spec:
    (name_fragment DOT!)? name_fragment
    { __markRule(COLUMN_SPEC);}
    ;


column_name_ref:
    identifier2
    { __markRule(COLUMN_NAME_REF);}
    ;

column_name_ddl:
    identifier2
    { __markRule(COLUMN_NAME_DDL);}
    ;

trim_function :
    "trim"! OPEN_PAREN! ( "leading" | "trailing" | "both") ?
    plsql_expression ( "from" plsql_expression )? CLOSE_PAREN!
    { __markRule(TRIM_FUNC);}
    ;


pseudo_column :
        "user"
        {  __markRule(USER_CONST); }
        | "sysdate"
        {  __markRule(SYSDATE_CONST); }
        | "systimestamp"
        {  __markRule(SYSTIMESTAMP_CONST); }
        | "current_timestamp"
        {  __markRule(CURRENT_TIMESTAMP_CONST); }
        | "dbtimezone"
        {  __markRule(DBTIMEZONE); }
        | "rownum"
        {  __markRule(ROWNUM); }
        ;

selected_table:
    ("table")=> row_proc ( alias )?
    {  __markRule(VIRTUAL_TABLE); }
    | ("the") => the_proc ( alias )?
    | from_subquery   ///subquery ( alias )?
    | table_alias
    ;

from_subquery:
    subquery ( alias )?
    {  __markRule(FROM_SUBQUERY); }
    ;

from_plain_table:
    table_spec ( alias )?
    {  __markRule(FROM_PLAIN_TABLE); }
    ;

ansi_spec :
      ( "inner"
        | (( "left" | "right") ("outer")? )
      )? "join"
      selected_table
      "on"
      condition
      {  __markRule(ANSI_JOIN_TAB_SPEC); }
      ;


row_proc:
    "table"! OPEN_PAREN!  ( select_command | cast_proc | function_call | identifier ) CLOSE_PAREN!
    ;


the_proc:
    "the" subquery
    ;

cast_proc :
    "cast"! OPEN_PAREN! plsql_expression "as"! (type_name_ref|datatype) CLOSE_PAREN!
    {  __markRule(CAST_EXPR); }
    ;

table_spec:
//    ( schema_name DOT )? table_name ( AT_SIGN link_name )?
    ( schema_name DOT )? table_name2 //( AT_PREFIXED )?
    ;

table_name2 :
    identifier2
        { __markRule(TABLE_NAME);}
    | TABLE_NAME_SPEC
        { __markRule(TABLE_NAME_WITH_LINK);}
    ;

table_alias:
    table_spec ( alias )?
    {  __markRule(TABLE_ALIAS); }
    ;

link_name:
    identifier
    ;

relational_op:
    ( EQ
    | LT
    | GT
    | NOT_EQ
    | LE
    | GE )
    {  __markRule(RELATION_OP); }
    ;


exp_set:
    ( OPEN_PAREN "select" ) => subquery
    | parentesized_plsql_exp_list
    | plsql_expression
    ;
/*
connect_clause:
    ( "start" "with" condition )? // The start can precede the connect by
    "connect" "by"
        condition
        |  ( "connect" "by" condition ) ?
     "start" w2:"with" condition
    {  __markRule(CONNECT_CLAUSE); }
    ;
*/

connect_clause:
    connect_clause_internal (connect_clause_internal)?
    {  __markRule(CONNECT_CLAUSE); }
    ;

connect_clause_internal:
    ("start" "with" condition) // The start can precede the connect by
    | ("connect" "by" condition)
    ;

group_clause:
    (
        ("group"! "by"! plsql_expression (c:COMMA! plsql_expression )*)
        | ( "having" condition )
    )+
    {  __markRule(GROUP_CLAUSE); }
    ;
/*
groupby_expr_list:
    plsql_expression (c:COMMA! plsql_expression )*
    {
        if(#c != null)
            {#.groupby_expr_list = #.([GROUPBY_EXPR_LIST, "GROUPBY_EXPR_LIST" ], #.groupby_expr_list);}
    }
    ;
*/

order_clause:
    "order"! "by"!
    sorted_def ( COMMA! sorted_def )*
    {  __markRule(ORDER_CLAUSE); }
    ;

sorted_def:
    plsql_expression (( a:"asc" | d:"desc" ) ("nulls" ("first" |"last"))? )?
    {  __markRule(SORTED_DEF); }
    ;

update_clause:
    "for" "update"
    ( "of" column_name_ref ( COMMA column_name_ref )* )?
    ( "nowait"|("wait" numeric_literal) )?
    {  __markRule(FOR_UPDATE_CLAUSE); }
    ;

insert_command:
    "insert"! "into"!
        (
          (table_alias) => table_alias ( OPEN_PAREN! column_spec_list CLOSE_PAREN! )?
                ( ( "values"! (parentesized_plsql_exp_list | variable_ref)) | select_expression ) (returning)?
            {  __markRule(INSERT_COMMAND); }

         // To define the set of rows to be inserted into the target table of an INSERT statement
         | subquery ( "values"! (parentesized_plsql_exp_list | function_call) )
            {  __markRule(INSERT_INTO_SUBQUERY_COMMAND); }
         )
    ;

column_spec_list:
    column_spec ( COMMA! column_spec )*
    {  __markRule(COLUMN_SPEC_LIST); }
    ;

update_command:
    (   ( subquery_update ) => subquery_update
        | simple_update )
    {  __markRule(UPDATE_COMMAND); }
    ;

merge_command:
    "merge"! "into"! table_alias
    "using"! ( table_alias| (subquery ( alias )?) ) "on" condition
    when_action (when_action)?
    ("delete" "where" condition)?
    {  __markRule(MERGE_COMMAND); }
    ;

when_action:
    "when" ("not")? "matched"! "then"!
    (
        ("update" "set" column_spec EQ plsql_expression ( COMMA! column_spec EQ plsql_expression )*)
        | insert_columns
    ) (where_condition)?
    {  __markRule(MERGE_WHEN_COMMAND); }
    ;

insert_columns:
    "insert"! ( OPEN_PAREN! column_spec_list CLOSE_PAREN! )?
    "values"! parentesized_plsql_exp_list
    ;


simple_update:
    "update"! table_alias
    "set" column_spec EQ plsql_expression ( COMMA! column_spec EQ plsql_expression )*
    ( where_condition ) ?
    ( returning )?
    {  __markRule(SIMPLE_UPDATE_COMMAND); }
    ;


returning:
//      RETURNING id INTO l_fst_ids(indx);
//      RETURNING id,upd_cnt INTO o_alertId,o_updateCtr;
    ("returning"! | "return"!) identifier2 (COMMA identifier2)* "into"! plsql_expression (COMMA plsql_expression)*
    ;


subquery_update:
    "update"! table_alias
    "set"
    OPEN_PAREN! column_spec_list CLOSE_PAREN! EQ subquery
    ( where_condition )?
    {  __markRule(SUBQUERY_UPDATE_COMMAND); }
    ;

delete_command:
    "delete"! ( "from"! )? table_alias ( where_condition )? (returning)?
    {  __markRule(DELETE_COMMAND); }
    ;

set_transaction_command:
        "set" "transaction" r:"read" "only"
        ;

close_statement :
      "close" cursor_name
      ;

fetch_statement:
    "fetch" cursor_name ( "bulk" "collect" ) ? "into" variable_ref (COMMA! variable_ref )*
    ;

variable_ref:
    ( name_fragment DOT )* name_fragment
    { __markRule(PLSQL_VAR_REF);}
    ;

lock_table_statement:
        l:"lock" t:"table" table_reference_list
        i:"in" lock_mode m:"mode" ( n:"nowait" )?
        ;

lock_mode:
        r1:"row" s1:"share"
        | r2:"row" e1:"exclusive"
        |s2:"share" u:"update"
        | s3:"share"
        | s4:"share" r3:"row" e2:"exclusive"
        | e3:"exclusive"
        ;

open_statement:
        o:"open" cursor_name  (parentesized_plsql_exp_list)?
         ( f:"for" ( select_expression | plsql_expression ))?
//         ( "using" plsql_expr_list ) ?
         ( "using" "in" plsql_lvalue_list )?
        ;

rollback_statement:
        "rollback" ( "work" )?
        ( "to"! ( "savepoint" )? savepoint_name )?
        ( "comment"! string_literal! )?
        {  __markRule(ROLLBACK_STATEMENT); }
        ;

savepoint_statement:
        "savepoint" savepoint_name
        ;

savepoint_name:
        identifier
        ;

// Direct mappings to lexer.
identifier :
    ( IDENTIFIER | DOUBLE_QUOTED_STRING )
    ;

identifier2:
    ( IDENTIFIER
    | DOUBLE_QUOTED_STRING
    | PLSQL_ENV_VAR
    | "left"
    | "right"
    | "type"
    | "count"
    | "open"
    | "exec"
    | "execute"
    | "user"
//    | "date"  todo -- is it legal to have identifer DATE??
    | "dbtimezone"
    | "execute"
    | "commit"
    | "rollback"
    | "savepoint"
    | "comment"
    | "charset"
    | "body"
    | "escape"
    | "reverse"
    | "exists"
    | "delete"
    | "trim"
    | "flush"
    | "interval"
    | "transaction"
    | "session"
    | "close"
    | "read"
    | "immediate"
    | "replace"
    | "sid"
    | "local"
    | "time"
    | "name"
//    | "true"
//    | "false"
    | "default"
    | "package"
//    | "function"
    | "ref"
    | "byte"
    | "interface"
//    | "from_tz"
    | "extract"
    | "next"
    | "column"
    | "col"
    | "timestamp"
    | "found"
    | "notfound"
    | "rowcount"
    | "isopen"
    | "bulk_rowcount"
    | "bulk_exceptions"
    | "nocache"
    | "cache"
    | "compress"
    | "deterministic"
    | "degree"
    | "instances"
    | "range"
    | "parallel"
    | "noparallel"
    | "year"
    | "month"
    | "day"
    | "row"
    | "buffer_pool"
    | "system"
    | "error_code"
    | "error_index"
    | "temporary"
    | "aggregate"
    | "current"
    | "sqlcode"
    | "sqlerrm"
    | "force"
    | "cascade"
    | "constraints"
    | "purge"
    | "validate"
    | "nextval"
    | "currval"
    | "rows"
    | "foreign"
    | "primary"
    | "records"
    | "parameters"
    | "access"
    | "newline"
    | "delimited"
    | "fixed"
    | "characterset"
    | "big"
    | "little"
    | "endian"
    | "mark"
    | "check"
    | "nocheck"
    | "string"
    | "sizes"
    | "bytes"
    | "load"
    | "nobadfile"
    | "badfile"
    | "nodiscardfile"
    | "discardfile"
    | "nologfile"
    | "logfile"
    | "readsize"
    | "skip"
    | "data_cache"
    | "fields"
    | "missing"
    | "field"
    | "reject"
    | "with"
    | "lrtrim"
    | "notrim"
    | "ltrim"
    | "rtrim"
    | "ldtrim"
    | "position"
    | "enclosed"
    | "date_format"
    | "varraw"
    | "varcharc"
    | "varrawc"
    | "oracle_number"
    | "oracle_date"
    | "counted"
    | "external"
    | "zoned"
    | "unsigned"
    | "location"
    | "limit"
    | "unlimited"
    | "concat"
    | "clob"
    | "blob"
    | "lobfile"
    | "float"
    | "preprocessor"
    | "compression"
    | "enabled"
    | "disabled"
    | "encryption"
    | "encrypt"
    | "action"
    | "version"
    | "compatible"
    | "data"
    | "no"
    | "initrans"
    | "maxtrans"
    | "logging"
    | "nologging"
    | "quit"
    | "spool"
    | "def"
    | "define"
    | "novalidate"
    | "heap"
    | "freelists"
    | "freelist"
    | "organization"
    | "rely"
    | "at"
    | "on"
    | "off"
    | "enable"
    | "disable"
    | "sql"
    | "before"
    | "after"
    | "directory"
    | "mask"
    | "terminated"
    | "whitespace"
    | "optionally"
    | "option"
    | "operations"
    | "startup"
    | "shutdown"
    | "servererror"
    | "logon"
    | "logoff"
    | "associate"
    | "statistics"
    | "audit"
    | "noaudit"
    | "ddl"
    | "diassociate"
    | "grant"
    | "rename"
    | "truncate"
    | "revoke"
    | "new"
    | "old"
    | "schema"
    | "hash"
    | "precision"
    | "key"
    | "monitoring"
    | "collect"
    | "nulls"
    | "first"
    | "last"
    | "timezone"
    | "language"
    | "java"
    | "store"
    | "library"
    | "role"
    | "online"
    | "compute"
    | "continue"
    | "var"
    | "variable"
    | "none"
    | "oserror"
    | "sqlerror"
    | "whenever"
    | "the"
    | "identified"
    | "link"
    | "by"
    | "noorder"
    | "maxvalue"
    | "minvalue"
    | "increment"
//    | "start" -- it is a keyword!!!
    | "cycle"
    | "nocycle"
    | "pctthreshold"
    | "including"
    | "repheader"
    | "repfooter"
    | "serveroutput"
    | "groups"
    | "wait"
    | "indices"
    )
    ;


/////////////////////////////////////////////////////////////////
/////       EXTERNAL TABLE SPECIFICATION        /////////////////
/////////////////////////////////////////////////////////////////
 
external_table_spec:
    "external"! OPEN_PAREN! "type" (oracle_loader_params|oracle_datapump_params)location CLOSE_PAREN!
//    ("as" select_expression)? (reject_spec|parallel_clause)*
    (reject_spec|parallel_clause)*
    ;

oracle_loader_params:
    "oracle_loader" directory_spec (access_parameters)?
    ;

oracle_datapump_params:
    "oracle_datapump" directory_spec (write_access_parameters)?
    ;

directory_spec:
    ("default")? "directory" identifier
    ;

write_access_parameters:
    "access" "parameters"
        OPEN_PAREN!
            ("nologfile"|("logfile" file_location_spec))?
            ("version" ("compatible"|"latest"|string_literal))?
            ("compression" ("enabled"|"disabled"))?
            ("encryption" ("enabled"|"disabled"))?
        CLOSE_PAREN!
    ;

access_parameters:
    "access" "parameters"
        OPEN_PAREN!
             (record_format_info)? (field_definitions)? (column_transforms)? 
        CLOSE_PAREN!
    ;

record_format_info:
    "records" rec_format (rec_format_tail)*
    ;
    
rec_format:
    ("fixed" numeric_literal)
    | ("variable" numeric_literal)
    | ("delimited" "by" ("newline"|string_literal))
    ;

rec_format_tail:
    ("characterset" (string_literal | identifier) )
    | ("data" "is" ("big"|"little") "endian")
    | ("byte" "order" "mark" ("check"|"nocheck"))
    | ("string" "sizes" "in" ("bytes"|"characters"))
    | ("load" "when" condition)
    | ("nobadfile"|("badfile" file_location_spec) )
    | ("nodiscardfile"|("discardfile" file_location_spec) )
    | ("nologfile"|("logfile" file_location_spec) )
    | ( ("readsize"|"data_cache"|"skip") NUMBER)
    | ( "preprocessor" file_location_spec)
    ;

field_definitions:
    "fields" (delim_spec)? (trim_spec)?
        ("missing" "field" "values" "are" "null")?
        ("reject" "rows" "with" "all" "null" "fields")?
        (field_list)?
    ;

column_transforms:
    "column" "transforms" OPEN_PAREN transform (COMMA transform)* CLOSE_PAREN
    ;

transform:
    identifier2 "from" (
        "null"
        | const_str
        | ("concat" OPEN_PAREN! (field_name|const_str) (COMMA! (field_name|const_str))* CLOSE_PAREN!)
        | ("lobfile"
                OPEN_PAREN! (field_name|(const_str COLON!)) (COMMA! (field_name|(const_str COLON!)))* CLOSE_PAREN!
                (lobfile_attr_list)?
          )
    )
    ;

lobfile_attr_list:
    ("from" OPEN_PAREN identifier2 (COMMA identifier2)* CLOSE_PAREN)
    | "clob"
    | "blob"
// todo --    | "characterset" EQ charcater set name
    ;

const_str:
    "constant" string_literal
    ;

// The delim_spec clause is used to find the end (and if ENCLOSED BY is specified, the start) of a field.
delim_spec:
    ("enclosed" "by" string_literal ( "and" string_literal )?)
    | ("terminated" "by" ("whitespace"| string_literal| DOUBLE_QUOTED_STRING)
        (("optionally")? "enclosed" "by" string_literal ( "and" string_literal )?)?
      )
    ;

// The trim_spec clause is used to specify that spaces should be trimmed from the beginning
// of a text field, the end of a text field, or both.
trim_spec:
    "lrtrim" | "notrim" | "ltrim" | "rtrim" | "ldrtrim" 
    ;

field_list:
    OPEN_PAREN! field_spec (COMMA! field_spec)* CLOSE_PAREN!
    ;

field_spec:
    identifier2 (pos_spec)? (datatype_spec)?
    ;

pos_spec:
    "position"! OPEN_PAREN! (ASTERISK)? (PLUS|MINUS)? numeric_literal COLON (PLUS)? numeric_literal CLOSE_PAREN!
    ;

datatype_spec:
    (("unsigned")? "integer" ("external")? (OPEN_PAREN NUMBER CLOSE_PAREN)? (delim_spec)?)
    | ( ("decimal"|"zoned")
        ( ("external") (OPEN_PAREN NUMBER CLOSE_PAREN)? (delim_spec)?
         | (OPEN_PAREN NUMBER CLOSE_PAREN) )?
      )
    | "oracle_date"
    | ("oracle_number" ("counted")?)
    | ("float" ("external")? (OPEN_PAREN NUMBER CLOSE_PAREN)? (delim_spec)?)
    | "double"
    | ("raw" (OPEN_PAREN NUMBER CLOSE_PAREN)?)
    | ( "char" (OPEN_PAREN NUMBER CLOSE_PAREN)? (delim_spec)? (trim_spec)? (date_format_spec)? )
    | (("varchar"| "varraw" | "varcharc" | "varrawc") (OPEN_PAREN NUMBER (COMMA NUMBER)? CLOSE_PAREN)?)  
    ;


// DATE_FORMAT TIMESTAMP MASK  'YYYY-MM-DD HH24:MI:SS.FF9'
date_format_spec:
    "date_format" ("timestamp"|"date") ("with" "timezone")? "mask" (string_literal|DOUBLE_QUOTED_STRING)
    ;

location:
    "location"
        OPEN_PAREN
            (file_location_spec| string_literal ) (COMMA! (file_location_spec|string_literal))*
        CLOSE_PAREN
    ;

file_location_spec:
    (identifier COLON)? string_literal
    ;
