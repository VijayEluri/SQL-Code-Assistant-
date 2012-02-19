// $ANTLR 2.7.5 (20050128): "/git_app/sqlassistant_super/github/shared/root/grammars/plsql_parser_ex.g" -> "PLSqlParserAdopted.java"$

package com.deepsky.generated.plsql.adopted;

public interface PLSqlTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int BAD_ML_COMMENT = 4;
	int BAD_CHARACTER = 5;
	int BAD_CHAR_LITERAL = 6;
	int BAD_NUMBER_LITERAL = 7;
	int STORAGE_SIZE = 8;
	int LT = 9;
	int LE = 10;
	int GE = 11;
	int NOT_EQ = 12;
	int CUSTOM_TOKEN = 13;
	int COLON_NEW = 14;
	int COLON_OLD = 15;
	int TABLE_NAME_SPEC = 16;
	int PLSQL_ENV_VAR = 17;
	int ANY_CHARACTER = 18;
	int IDENTIFIER = 19;
	int QUOTED_STR = 20;
	int DOUBLE_QUOTED_STRING = 21;
	int AT_PREFIXED = 22;
	int SEMI = 23;
	int COLON = 24;
	int DOT = 25;
	int COMMA = 26;
	int ASTERISK = 27;
	int OPEN_PAREN = 28;
	int CLOSE_PAREN = 29;
	int PLUS = 30;
	int MINUS = 31;
	int DIVIDE = 32;
	int BACKSLASH = 33;
	int EQ = 34;
	int PERCENTAGE = 35;
	int DOUBLEDOT = 36;
	int CONCAT = 37;
	int START_LABEL = 38;
	int DOLLAR = 39;
	int END_LABEL = 40;
	int ASSIGNMENT_EQ = 41;
	int PASS_BY_NAME = 42;
	int VERTBAR = 43;
	int GT = 44;
	int NUMBER = 45;
	int N = 46;
	int WS = 47;
	int LF = 48;
	int SL_COMMENT = 49;
	int ML_COMMENT = 50;
	int IF_COND_CMPL = 51;
	int THEN_COND_CMPL = 52;
	int ELSE_COND_CMPL = 53;
	int END_COND_CMPL = 54;
	int ERROR_COND_CMPL = 55;
	int START_RULE = 56;
	int ERROR_TOKEN_A = 57;
	int PACKAGE_SPEC = 58;
	int PACKAGE_BODY = 59;
	int PACKAGE_NAME = 60;
	int PACKAGE_OBJ_BODY = 61;
	int RECORD_TYPE_DECL = 62;
	int SELECT_EXPRESSION = 63;
	int SELECT_EXPRESSION_UNION = 64;
	int PLSQL_BLOCK = 65;
	int PLSQL_BLOCK_END = 66;
	int CURSOR_DECLARATION = 67;
	int VARIABLE_DECLARATION = 68;
	int PROCEDURE_BODY = 69;
	int FUNCTION_BODY = 70;
	int PARAMETER_SPEC = 71;
	int SQL_STATEMENT = 72;
	int IF_STATEMENT = 73;
	int LOOP_STATEMENT = 74;
	int STATEMENT = 75;
	int TABLE_REFERENCE_LIST = 76;
	int TABLE_REFERENCE_LIST_FROM = 77;
	int WHERE_CONDITION = 78;
	int SUBQUERY = 79;
	int SQL_IDENTIFIER = 80;
	int FUNCTION = 81;
	int GROUP_FUNCTION = 82;
	int USER_FUNCTION = 83;
	int MULTIPLY = 84;
	int ARGUMENT = 85;
	int ARGUMENT_LIST = 86;
	int FIELD_NAME = 87;
	int PLSQL_EXPR_LIST_USING = 88;
	int EXPR_LIST = 89;
	int DECLARE_LIST = 90;
	int FUNCTION_CALL = 91;
	int PACKAGE_INIT_SECTION = 92;
	int CALL_ARGUMENT_LIST = 93;
	int SPEC_CALL_ARGUMENT_LIST = 94;
	int QUERY_PARTITION_CLAUSE = 95;
	int EXTRACT_OPTIONS = 96;
	int CALL_ARGUMENT = 97;
	int BASE_EXRESSION = 98;
	int COLUMN_SPEC = 99;
	int LOGICAL_AND = 100;
	int LOGICAL_OR = 101;
	int CASE_EXPRESSION_SMPL = 102;
	int CASE_EXPRESSION_SRCH = 103;
	int CASE_STATEMENT = 104;
	int COUNT_FUNC = 105;
	int SQLPLUS_ANONYM_PLSQL_BLOCK = 106;
	int RANK_FUNCTION = 107;
	int LEAD_FUNCTION = 108;
	int LAG_FUNCTION = 109;
	int TRIM_FUNC = 110;
	int DECODE_FUNC = 111;
	int COLUMN_SPEC_LIST = 112;
	int INSERT_COMMAND = 113;
	int UPDATE_COMMAND = 114;
	int DELETE_COMMAND = 115;
	int MERGE_COMMAND = 116;
	int MERGE_WHEN_COMMAND = 117;
	int STRING_LITERAL = 118;
	int NUMERIC_LITERAL = 119;
	int BOOLEAN_LITERAL = 120;
	int RETURN_TYPE = 121;
	int TYPE_SPEC = 122;
	int VARIABLE_NAME = 123;
	int COLUMN_OUTER_JOIN = 124;
	int LOGICAL_FACTOR = 125;
	int TABLE_ALIAS = 126;
	int CAST_FUNC = 127;
	int VAR_REF = 128;
	int PLSQL_VAR_REF = 129;
	int PARAMETER_REF = 130;
	int EXCEPTION_SECTION = 131;
	int SELECTED_TABLE = 132;
	int CREATE_PROCEDURE = 133;
	int CREATE_FUNCTION = 134;
	int COMMIT_STATEMENT = 135;
	int ROLLBACK_STATEMENT = 136;
	int NULL_STATEMENT = 137;
	int ASSIGNMENT_STATEMENT = 138;
	int PROCEDURE_CALL = 139;
	int RETURN_STATEMENT = 140;
	int LOCK_TABLE_STATEMENT = 141;
	int OPEN_STATEMENT = 142;
	int FETCH_STATEMENT = 143;
	int SET_TRN_STATEMENT = 144;
	int CLOSE_STATEMENT = 145;
	int OBJECT_NAME = 146;
	int PARAMETER_NAME = 147;
	int DEFAULT = 148;
	int THEN_STATEMENT = 149;
	int ELSIF_STATEMENT = 150;
	int ELSE_STATEMENT = 151;
	int STATEMENT_LIST = 152;
	int RELATION_CONDITION = 153;
	int ISNULL_CONDITION = 154;
	int LIKE_CONDITION = 155;
	int BETWEEN_CONDITION = 156;
	int IN_CONDITION = 157;
	int PARENTHESIZED_EXPR = 158;
	int ORDER_CLAUSE = 159;
	int CONNECT_CLAUSE = 160;
	int GROUP_CLAUSE = 161;
	int INTO_CLAUSE = 162;
	int FOR_UPDATE_CLAUSE = 163;
	int PARTITION_NAME = 164;
	int ASTERISK1 = 165;
	int ROWCOUNT_EXRESSION = 166;
	int MULTIPLICATION_OP = 167;
	int DIVIDE_OP = 168;
	int PLUS_OP = 169;
	int MINUS_OP = 170;
	int CONCAT_OP = 171;
	int CURSOR_NAME = 172;
	int CURSOR_NAME_REF = 173;
	int DATATYPE = 174;
	int EXTRACT_DATE_FUNC = 175;
	int ANSI_JOIN_TAB_SPEC = 176;
	int INTERVAL_DAY_TO_SEC_EXPR = 177;
	int INTERVAL_YEAR_TO_MONTH_EXPR = 178;
	int INTERVAL_CONST = 179;
	int USER_CONST = 180;
	int SYSDATE_CONST = 181;
	int SYSTIMESTAMP_CONST = 182;
	int CURRENT_TIMESTAMP_CONST = 183;
	int ALIAS_NAME = 184;
	int EXISTS_EXPR = 185;
	int SUBQUERY_EXPR = 186;
	int TYPE_NAME_REF = 187;
	int TYPE_NAME = 188;
	int SORTED_DEF = 189;
	int RAISE_STATEMENT = 190;
	int PACKAGE_OBJ_SPEC = 191;
	int PROCEDURE_SPEC = 192;
	int SIMPLE_UPDATE_COMMAND = 193;
	int SUBQUERY_UPDATE_COMMAND = 194;
	int IMMEDIATE_COMMAND = 195;
	int FUNCTION_SPEC = 196;
	int NEGATE_FACTOR = 197;
	int TABLE_TYPE_REF = 198;
	int COLUMN_TYPE_REF = 199;
	int STATEMENT_ANNOT = 200;
	int ASTERISK_COLUMN = 201;
	int IDENT_ASTERISK_COLUMN = 202;
	int EXPR_COLUMN = 203;
	int TABLE_REF = 204;
	int TABLE_REF_WITH_LINK = 205;
	int FROM_SUBQUERY = 206;
	int FROM_PLAIN_TABLE = 207;
	int SCHEMA_NAME = 208;
	int COLUMN_NAME_REF = 209;
	int ARITHMETIC_EXPR = 210;
	int ASSIGNMENT_OP = 211;
	int DBTIMEZONE = 212;
	int SUBQUERY_CONDITION = 213;
	int RECORD_ITEM = 214;
	int EXCEPTION_DECL = 215;
	int COMPLEX_NAME = 216;
	int CHARACTER_SET = 217;
	int AUTHID = 218;
	int RESTRICT_REF_PRAGMA = 219;
	int AUTONOMOUS_TRN_PRAGMA = 220;
	int EXCEPTION_PRAGMA = 221;
	int FIPSFLAG_PRAGMA = 222;
	int BUILTIN_PRAGMA = 223;
	int INTERFACE_PRAGMA = 224;
	int TIMESTAMPG_PRAGMA = 225;
	int SERIALLY_REUSABLE_PRAGMA = 226;
	int TIMESTAMP_CONST = 227;
	int SUBTYPE_DECL = 228;
	int MEMBER_OF = 229;
	int SQLPLUS_SET = 230;
	int SQLPLUS_SHOW = 231;
	int SQLPLUS_DEFINE = 232;
	int SQLPLUS_VARIABLE = 233;
	int SQLPLUS_EXEC = 234;
	int SQLPLUS_WHENEVER = 235;
	int SQLPLUS_PROMPT = 236;
	int SQLPLUS_COLUMN = 237;
	int SQLPLUS_START = 238;
	int SQLPLUS_SERVEROUTPUT = 239;
	int SQLPLUS_REPFOOTER = 240;
	int SQLPLUS_REPHEADER = 241;
	int SQLPLUS_EXIT = 242;
	int SQLPLUS_QUIT = 243;
	int SQLPLUS_RUNSCRIPT = 244;
	int SQLPLUS_SPOOL = 245;
	int OR_LOGICAL = 246;
	int AND_LOGICAL = 247;
	int NOT_LOGICAL = 248;
	int LOGICAL_EXPR = 249;
	int RELATION_OP = 250;
	int SEQUENCE_EXPR = 251;
	int SEQUENCE_REF = 252;
	int ALIAS_IDENT = 253;
	int COLUMN_NAME_DDL = 254;
	int COLUMN_DEF = 255;
	int TABLE_DEF = 256;
	int TABLE_COLLECTION = 257;
	int VARRAY_COLLECTION = 258;
	int REF_CURSOR = 259;
	int OBJECT_TYPE_DEF = 260;
	int AT_TIME_ZONE_EXPR = 261;
	int TIMEZONE_SPEC = 262;
	int GRANT_COMMAND = 263;
	int REVOKE_COMMAND = 264;
	int ALTER_TABLE = 265;
	int ALTER_GENERIC = 266;
	int CREATE_TEMP_TABLE = 267;
	int COMMENT = 268;
	int COMMENT_STR = 269;
	int CREATE_INDEX = 270;
	int INSERT_INTO_SUBQUERY_COMMAND = 271;
	int CONDITIONAL_COMPILATION = 272;
	int COND_COMP_SEQ = 273;
	int COND_COMP_SEQ2 = 274;
	int COND_COMP_ERROR = 275;
	int COLUMN_NAME_LIST = 276;
	int OWNER_COLUMN_NAME_LIST = 277;
	int CREATE_VIEW = 278;
	int DATATYPE_PARAM_INFO = 279;
	int CREATE_VIEW_COLUMN_DEF = 280;
	int TABLE_FUNCTION = 281;
	int ROWNUM = 282;
	int ROWID = 283;
	int CUSTOM_AGGR_FUNCTION = 284;
	int LAST_STMT_RESULT_NUM = 285;
	int LAST_STMT_RESULT_BOOL = 286;
	int CURRENT_OF_CLAUSE = 287;
	int CURSOR_STATE = 288;
	int SQLCODE_SYSVAR = 289;
	int SQLERRM_SYSVAR = 290;
	int COLLECTION_METHOD_CALL = 291;
	int COLLECTION_METHOD_CALL2 = 292;
	int C_RECORD_ITEM_REF = 293;
	int CALLABLE_NAME_REF = 294;
	int TRUNCATE_TABLE = 295;
	int DROP_VIEW = 296;
	int DROP_TABLE = 297;
	int DROP_FUNCTION = 298;
	int DROP_PROCEDURE = 299;
	int DROP_PACKAGE = 300;
	int DROP_INDEX = 301;
	int DROP_SEQUENCE = 302;
	int DROP_TYPE = 303;
	int DROP_OPERATOR = 304;
	int DROP_SYNONYM = 305;
	int DROP_USER = 306;
	int DROP_ROLE = 307;
	int DROP_LIBRARY = 308;
	int DROP_DB_LINK = 309;
	int DROP_DIRECTORY = 310;
	int DROP_TRIGGER = 311;
	int CREATE_SYNONYM = 312;
	int SYNONYM_NAME = 313;
	int SYNONYM_OBJ = 314;
	int SYNONYM_OBJ_WITH_LINK = 315;
	int COLUMN_PK_SPEC = 316;
	int COLUMN_FK_SPEC = 317;
	int NOT_NULL_STMT = 318;
	int COLUMN_CHECK_CONSTRAINT = 319;
	int COLUMN_NOT_NULL_CONSTRAINT = 320;
	int PK_SPEC = 321;
	int FK_SPEC = 322;
	int UNIQUE_CONSTRAINT = 323;
	int CHECK_CONSTRAINT = 324;
	int CONSTRAINT_NAME = 325;
	int ADD_CONSTRAINT = 326;
	int ADD_PRIMARY_KEY = 327;
	int IOT_TYPE = 328;
	int HEAP_ORGINIZED = 329;
	int EXTERNAL_TYPE = 330;
	int EXTERNAL_TABLE_SPEC = 331;
	int WRITE_ACCESS_PARAMS_SPEC = 332;
	int LOADER_ACCESS_PARAMS = 333;
	int EXT_FIELD_SPEC_LIST = 334;
	int EXT_FIELD_SPEC = 335;
	int RECORD_FMT_SPEC = 336;
	int EXT_TABLE_LOCATION_SPEC = 337;
	int STORAGE_PARAM = 338;
	int STORAGE_SPEC = 339;
	int PARALLEL_CLAUSE = 340;
	int REJECT_SPEC = 341;
	int NAME_FRAGMENT = 342;
	int EXEC_NAME_REF = 343;
	int COLLECTION_METHOD_NAME = 344;
	int V_COLUMN_DEF = 345;
	int TABLE_NAME_DDL = 346;
	int VIEW_NAME = 347;
	int INDEX_NAME = 348;
	int VIEW_NAME_DDL = 349;
	int CREATE_TRIGGER = 350;
	int CREATE_DIRECTORY = 351;
	int CREATE_DB_LINK = 352;
	int CREATE_SEQUENCE = 353;
	int TRIGGER_FOR_EACH = 354;
	int TRIGGER_NAME = 355;
	int ALTER_TRIGGER = 356;
	int DB_EVNT_TRIGGER_CLAUSE = 357;
	int DDL_TRIGGER_CLAUSE = 358;
	int DML_TRIGGER_CLAUSE = 359;
	int TRIGGER_COLUMN_REF = 360;
	int INSTEADOF_TRIGGER = 361;
	int TRIGGER_TARGET = 362;
	int FORALL_LOOP_SPEC = 363;
	int CURSOR_REF_LOOP_SPEC = 364;
	int CURSOR_LOOP_SPEC = 365;
	int CURSOR_LOOP_INDEX = 366;
	int NUM_LOOP_INDEX = 367;
	int NUMERIC_LOOP_SPEC = 368;
	int RECORD_ITEM_NAME = 369;
	int GOTO_STATEMENT = 370;
	int EXIT_STATEMENT = 371;
	int LABEL_NAME = 372;
	int PARTITION_SPEC = 373;
	int RANGE_PARTITION = 374;
	int HASH_PARTITION = 375;
	int MONITORING_CLAUSE = 376;
	int CREATE_TABLESPACE = 377;
	int DROP_TABLESPACE = 378;
	int TABLESPACE_NAME = 379;
	int ALTER_TABLESPACE = 380;
	int CREATE_USER = 381;
	int CREATE_TYPE = 382;
	int BIND_VAR = 383;
	int RETURNING_CLAUSE = 384;
	int LITERAL_package = 385;
	int LITERAL_body = 386;
	int LITERAL_alter = 387;
	int LITERAL_drop = 388;
	int LITERAL_table = 389;
	int LITERAL_purge = 390;
	int LITERAL_view = 391;
	int LITERAL_cascade = 392;
	int LITERAL_constraints = 393;
	int LITERAL_function = 394;
	int LITERAL_procedure = 395;
	int LITERAL_index = 396;
	int LITERAL_force = 397;
	int LITERAL_sequence = 398;
	int LITERAL_type = 399;
	int LITERAL_validate = 400;
	int LITERAL_public = 401;
	int LITERAL_synonym = 402;
	int LITERAL_operator = 403;
	int LITERAL_user = 404;
	int LITERAL_role = 405;
	int LITERAL_directory = 406;
	int LITERAL_library = 407;
	int LITERAL_database = 408;
	int LITERAL_link = 409;
	int LITERAL_trigger = 410;
	int LITERAL_associate = 411;
	int LITERAL_statistics = 412;
	int LITERAL_with = 413;
	int LITERAL_column = 414;
	int LITERAL_functions = 415;
	int LITERAL_packages = 416;
	int LITERAL_types = 417;
	int LITERAL_indexes = 418;
	int LITERAL_indextypes = 419;
	int LITERAL_system = 420;
	int LITERAL_managed = 421;
	int LITERAL_storage = 422;
	int LITERAL_default = 423;
	int LITERAL_cost = 424;
	int LITERAL_selectivity = 425;
	int LITERAL_using = 426;
	int LITERAL_null = 427;
	int LITERAL_truncate = 428;
	int LITERAL_comment = 429;
	int LITERAL_on = 430;
	int LITERAL_is = 431;
	int LITERAL_not = 432;
	int LITERAL_disable = 433;
	int LITERAL_enable = 434;
	int LITERAL_row = 435;
	int LITERAL_movement = 436;
	int LITERAL_constraint = 437;
	int LITERAL_primary = 438;
	int LITERAL_key = 439;
	int LITERAL_references = 440;
	int LITERAL_rely = 441;
	int LITERAL_check = 442;
	int LITERAL_unique = 443;
	int LITERAL_sysdate = 444;
	int LITERAL_systimestamp = 445;
	int LITERAL_set = 446;
	int LITERAL_long = 447;
	int LITERAL_show = 448;
	int LITERAL_var = 449;
	int LITERAL_variable = 450;
	int LITERAL_col = 451;
	int LITERAL_exec = 452;
	int LITERAL_execute = 453;
	int LITERAL_whenever = 454;
	int LITERAL_sqlerror = 455;
	int LITERAL_oserror = 456;
	int LITERAL_exit = 457;
	int LITERAL_continue = 458;
	int LITERAL_commit = 459;
	int LITERAL_rollback = 460;
	int LITERAL_none = 461;
	int LITERAL_def = 462;
	int LITERAL_define = 463;
	int LITERAL_prompt = 464;
	int LITERAL_rem = 465;
	int LITERAL_host = 466;
	int LITERAL_quit = 467;
	int LITERAL_spool = 468;
	int LITERAL_sta = 469;
	int LITERAL_start = 470;
	int LITERAL_repfooter = 471;
	int LITERAL_off = 472;
	int LITERAL_repheader = 473;
	int LITERAL_serveroutput = 474;
	int LITERAL_begin = 475;
	int LITERAL_declare = 476;
	int DOUBLE_DOT = 477;
	int LITERAL_create = 478;
	int LITERAL_or = 479;
	int LITERAL_replace = 480;
	int LITERAL_global = 481;
	int LITERAL_temporary = 482;
	int LITERAL_identified = 483;
	int LITERAL_by = 484;
	int LITERAL_externally = 485;
	int LITERAL_as = 486;
	int LITERAL_globally = 487;
	int LITERAL_tablespace = 488;
	int LITERAL_quota = 489;
	int LITERAL_unlimited = 490;
	int LITERAL_profile = 491;
	int LITERAL_password = 492;
	int LITERAL_expire = 493;
	int LITERAL_account = 494;
	int LITERAL_lock = 495;
	int LITERAL_unlock = 496;
	int LITERAL_bigfile = 497;
	int LITERAL_smallfile = 498;
	int LITERAL_tempfile = 499;
	int LITERAL_undo = 500;
	int LITERAL_datafile = 501;
	int LITERAL_next = 502;
	int LITERAL_maxsize = 503;
	int LITERAL_size = 504;
	int LITERAL_reuse = 505;
	int LITERAL_extent = 506;
	int LITERAL_management = 507;
	int LITERAL_local = 508;
	int LITERAL_uniform = 509;
	int LITERAL_dictionary = 510;
	int LITERAL_retention = 511;
	int LITERAL_guarantee = 512;
	int LITERAL_noguarantee = 513;
	int LITERAL_autoextend = 514;
	int LITERAL_group = 515;
	int LITERAL_logging = 516;
	int LITERAL_nologging = 517;
	int LITERAL_no = 518;
	int LITERAL_online = 519;
	int LITERAL_offline = 520;
	int LITERAL_normal = 521;
	int LITERAL_immediate = 522;
	int LITERAL_read = 523;
	int LITERAL_only = 524;
	int LITERAL_write = 525;
	int LITERAL_permanent = 526;
	int LITERAL_including = 527;
	int LITERAL_contents = 528;
	int LITERAL_and = 529;
	int LITERAL_datafiles = 530;
	int LITERAL_add = 531;
	int LITERAL_rename = 532;
	int LITERAL_to = 533;
	int LITERAL_end = 534;
	int LITERAL_backup = 535;
	int LITERAL_coalesce = 536;
	int LITERAL_minimum = 537;
	int LITERAL_maxvalue = 538;
	int LITERAL_minvalue = 539;
	int LITERAL_cycle = 540;
	int LITERAL_nocycle = 541;
	int LITERAL_cache = 542;
	int LITERAL_nocache = 543;
	int LITERAL_increment = 544;
	int LITERAL_order = 545;
	int LITERAL_noorder = 546;
	int LITERAL_for = 547;
	int LITERAL_connect = 548;
	int LITERAL_after = 549;
	int LITERAL_before = 550;
	int LITERAL_startup = 551;
	int LITERAL_shutdown = 552;
	int LITERAL_servererror = 553;
	int LITERAL_logon = 554;
	int LITERAL_logoff = 555;
	int LITERAL_analyze = 556;
	int LITERAL_audit = 557;
	int LITERAL_noaudit = 558;
	int LITERAL_ddl = 559;
	int LITERAL_diassociate = 560;
	int LITERAL_grant = 561;
	int LITERAL_revoke = 562;
	int LITERAL_schema = 563;
	int LITERAL_instead = 564;
	int LITERAL_of = 565;
	int LITERAL_delete = 566;
	int LITERAL_insert = 567;
	int LITERAL_update = 568;
	int LITERAL_each = 569;
	int LITERAL_referencing = 570;
	int LITERAL_old = 571;
	int LITERAL_new = 572;
	int LITERAL_when = 573;
	int LITERAL_bitmap = 574;
	int LITERAL_asc = 575;
	int LITERAL_desc = 576;
	int LITERAL_preserve = 577;
	int LITERAL_rows = 578;
	int LITERAL_nested = 579;
	int LITERAL_store = 580;
	int LITERAL_return = 581;
	int LITERAL_locator = 582;
	int LITERAL_value = 583;
	int LITERAL_lob = 584;
	int LITERAL_chunk = 585;
	int LITERAL_reads = 586;
	int LITERAL_pctversion = 587;
	int LITERAL_freepools = 588;
	int LITERAL_in = 589;
	int LITERAL_segment = 590;
	int LITERAL_creation = 591;
	int LITERAL_deferred = 592;
	int LITERAL_cluster = 593;
	int LITERAL_pctthreshold = 594;
	int LITERAL_filesystem_like_logging = 595;
	int LITERAL_compress = 596;
	int LITERAL_all = 597;
	int LITERAL_direct_load = 598;
	int LITERAL_operations = 599;
	int LITERAL_nocompress = 600;
	int LITERAL_pctfree = 601;
	int LITERAL_pctused = 602;
	int LITERAL_initrans = 603;
	int LITERAL_maxtrans = 604;
	int LITERAL_compute = 605;
	int LITERAL_parallel = 606;
	int LITERAL_noparallel = 607;
	int LITERAL_monitoring = 608;
	int LITERAL_nomonitoring = 609;
	int LITERAL_overflow = 610;
	int LITERAL_partition = 611;
	int LITERAL_range = 612;
	int LITERAL_interval = 613;
	int LITERAL_values = 614;
	int LITERAL_less = 615;
	int LITERAL_than = 616;
	int LITERAL_hash = 617;
	int LITERAL_partitions = 618;
	int LITERAL_novalidate = 619;
	int LITERAL_organization = 620;
	int LITERAL_heap = 621;
	int LITERAL_external = 622;
	int LITERAL_degree = 623;
	int LITERAL_instances = 624;
	int LITERAL_reject = 625;
	int LITERAL_limit = 626;
	int LITERAL_initial = 627;
	int LITERAL_minextents = 628;
	int LITERAL_maxextents = 629;
	int LITERAL_pctincrease = 630;
	int LITERAL_freelists = 631;
	int LITERAL_freelist = 632;
	int LITERAL_groups = 633;
	int LITERAL_optimal = 634;
	int LITERAL_buffer_pool = 635;
	int LITERAL_keep = 636;
	int LITERAL_recycle = 637;
	int LITERAL_flash_cache = 638;
	int LITERAL_cell_flash_cache = 639;
	int LITERAL_encrypt = 640;
	int LITERAL_foreign = 641;
	int LITERAL_restrict = 642;
	int LITERAL_action = 643;
	int LITERAL_modify = 644;
	int LITERAL_sort = 645;
	int LITERAL_nosort = 646;
	int LITERAL_reverse = 647;
	int LITERAL_visible = 648;
	int LITERAL_novisible = 649;
	int LITERAL_under = 650;
	int LITERAL_final = 651;
	int LITERAL_object = 652;
	int LITERAL_record = 653;
	int LITERAL_ref = 654;
	int LITERAL_cursor = 655;
	int LITERAL_rowtype = 656;
	int LITERAL_varray = 657;
	int LITERAL_option = 658;
	// "view_column_def_$internal$" = 659
	int LITERAL_timestamp = 660;
	int LITERAL_authid = 661;
	int LITERAL_wrapped = 662;
	int LITERAL_pragma = 663;
	int LITERAL_restrict_references = 664;
	int LITERAL_interface = 665;
	int LITERAL_builtin = 666;
	int LITERAL_fipsflag = 667;
	int LITERAL_exception_init = 668;
	int LITERAL_constant = 669;
	int LITERAL_subtype = 670;
	int LITERAL_loop = 671;
	int LITERAL_while = 672;
	int LITERAL_forall = 673;
	int LITERAL_if = 674;
	int LITERAL_goto = 675;
	int LITERAL_raise = 676;
	int LITERAL_case = 677;
	int LITERAL_select = 678;
	int LITERAL_merge = 679;
	int LITERAL_open = 680;
	int LITERAL_close = 681;
	int LITERAL_fetch = 682;
	int LITERAL_sql = 683;
	int LITERAL_found = 684;
	int LITERAL_notfound = 685;
	int LITERAL_rowcount = 686;
	int LITERAL_isopen = 687;
	int LITERAL_bulk_rowcount = 688;
	int LITERAL_bulk_exceptions = 689;
	int LITERAL_error_index = 690;
	int LITERAL_error_code = 691;
	int LITERAL_count = 692;
	int LITERAL_then = 693;
	int LITERAL_else = 694;
	int LITERAL_autonomous_transaction = 695;
	int LITERAL_prior = 696;
	int LITERAL_number = 697;
	int LITERAL_binary_integer = 698;
	int LITERAL_natural = 699;
	int LITERAL_positive = 700;
	int LITERAL_char = 701;
	int LITERAL_byte = 702;
	int LITERAL_raw = 703;
	int LITERAL_boolean = 704;
	int LITERAL_date = 705;
	int LITERAL_time = 706;
	int LITERAL_zone = 707;
	int LITERAL_year = 708;
	int LITERAL_month = 709;
	int LITERAL_day = 710;
	int LITERAL_second = 711;
	int LITERAL_smallint = 712;
	int LITERAL_real = 713;
	int LITERAL_numeric = 714;
	int LITERAL_int = 715;
	int LITERAL_integer = 716;
	int LITERAL_pls_integer = 717;
	int LITERAL_double = 718;
	int LITERAL_precision = 719;
	int LITERAL_float = 720;
	int LITERAL_decimal = 721;
	int LITERAL_varchar = 722;
	// "varchar2" = 723
	int LITERAL_nvarchar = 724;
	// "nvarchar2" = 725
	int LITERAL_character = 726;
	int LITERAL_rowid = 727;
	int LITERAL_blob = 728;
	int LITERAL_clob = 729;
	int LITERAL_nclob = 730;
	int LITERAL_bfile = 731;
	int LITERAL_out = 732;
	int LITERAL_nocopy = 733;
	int LITERAL_any_cs = 734;
	int LITERAL_charset = 735;
	int LITERAL_exception = 736;
	int LITERAL_serially_reusable = 737;
	int LITERAL_pipelined = 738;
	int LITERAL_parallel_enable = 739;
	int LITERAL_deterministic = 740;
	int LITERAL_language = 741;
	int LITERAL_java = 742;
	int LITERAL_name = 743;
	int LITERAL_save = 744;
	int LITERAL_exceptions = 745;
	int LITERAL_indices = 746;
	int LITERAL_true = 747;
	int LITERAL_false = 748;
	// "**" = 749
	int LITERAL_at = 750;
	int LITERAL_current = 751;
	int LITERAL_exists = 752;
	int LITERAL_like = 753;
	int LITERAL_escape = 754;
	int LITERAL_between = 755;
	int LITERAL_member = 756;
	int LITERAL_sqlcode = 757;
	int LITERAL_sqlerrm = 758;
	int LITERAL_cast = 759;
	int LITERAL_decode = 760;
	int LITERAL_trim = 761;
	int LITERAL_multiset = 762;
	int LITERAL_lag = 763;
	int LITERAL_lead = 764;
	int LITERAL_rank = 765;
	int LITERAL_dense_rank = 766;
	int LITERAL_extract = 767;
	int LITERAL_minute = 768;
	int LITERAL_hour = 769;
	int LITERAL_any = 770;
	int LITERAL_nextval = 771;
	int LITERAL_currval = 772;
	int LITERAL_over = 773;
	int LITERAL_sessiontimezone = 774;
	int LITERAL_dbtimezone = 775;
	int LITERAL_distinct = 776;
	int LITERAL_from = 777;
	int LITERAL_timezone_hour = 778;
	int LITERAL_timezone_minute = 779;
	int LITERAL_timezone_region = 780;
	int LITERAL_timezone_abbr = 781;
	int LITERAL_leading = 782;
	int LITERAL_trailing = 783;
	int LITERAL_both = 784;
	int LITERAL_work = 785;
	int LITERAL_elsif = 786;
	int LITERAL_privileges = 787;
	int LITERAL_union = 788;
	int LITERAL_intersect = 789;
	int LITERAL_minus = 790;
	int LITERAL_bulk = 791;
	int LITERAL_collect = 792;
	int LITERAL_into = 793;
	int LITERAL_session = 794;
	int LITERAL_flush = 795;
	int LITERAL_shared_pool = 796;
	int LITERAL_reset = 797;
	int LITERAL_sid = 798;
	int LITERAL_left = 799;
	int LITERAL_right = 800;
	int LITERAL_inner = 801;
	int LITERAL_outer = 802;
	int LITERAL_join = 803;
	int LITERAL_full = 804;
	int LITERAL_where = 805;
	int LITERAL_current_timestamp = 806;
	int LITERAL_rownum = 807;
	int LITERAL_the = 808;
	int LITERAL_having = 809;
	int LITERAL_nulls = 810;
	int LITERAL_first = 811;
	int LITERAL_last = 812;
	int LITERAL_nowait = 813;
	int LITERAL_wait = 814;
	int LITERAL_matched = 815;
	int LITERAL_returning = 816;
	int LITERAL_transaction = 817;
	int LITERAL_mode = 818;
	int LITERAL_share = 819;
	int LITERAL_exclusive = 820;
	int LITERAL_savepoint = 821;
	int LITERAL_oracle_loader = 822;
	int LITERAL_oracle_datapump = 823;
	int LITERAL_access = 824;
	int LITERAL_parameters = 825;
	int LITERAL_nologfile = 826;
	int LITERAL_logfile = 827;
	int LITERAL_version = 828;
	int LITERAL_compatible = 829;
	int LITERAL_latest = 830;
	int LITERAL_compression = 831;
	int LITERAL_enabled = 832;
	int LITERAL_disabled = 833;
	int LITERAL_encryption = 834;
	int LITERAL_records = 835;
	int LITERAL_fixed = 836;
	int LITERAL_delimited = 837;
	int LITERAL_newline = 838;
	int LITERAL_characterset = 839;
	int LITERAL_data = 840;
	int LITERAL_big = 841;
	int LITERAL_little = 842;
	int LITERAL_endian = 843;
	int LITERAL_mark = 844;
	int LITERAL_nocheck = 845;
	int LITERAL_string = 846;
	int LITERAL_sizes = 847;
	int LITERAL_bytes = 848;
	int LITERAL_characters = 849;
	int LITERAL_load = 850;
	int LITERAL_nobadfile = 851;
	int LITERAL_badfile = 852;
	int LITERAL_nodiscardfile = 853;
	int LITERAL_discardfile = 854;
	int LITERAL_readsize = 855;
	int LITERAL_data_cache = 856;
	int LITERAL_skip = 857;
	int LITERAL_preprocessor = 858;
	int LITERAL_fields = 859;
	int LITERAL_missing = 860;
	int LITERAL_field = 861;
	int LITERAL_are = 862;
	int LITERAL_transforms = 863;
	int LITERAL_concat = 864;
	int LITERAL_lobfile = 865;
	int LITERAL_enclosed = 866;
	int LITERAL_terminated = 867;
	int LITERAL_whitespace = 868;
	int LITERAL_optionally = 869;
	int LITERAL_lrtrim = 870;
	int LITERAL_notrim = 871;
	int LITERAL_ltrim = 872;
	int LITERAL_rtrim = 873;
	int LITERAL_ldrtrim = 874;
	int LITERAL_position = 875;
	int LITERAL_unsigned = 876;
	int LITERAL_zoned = 877;
	int LITERAL_oracle_date = 878;
	int LITERAL_oracle_number = 879;
	int LITERAL_counted = 880;
	int LITERAL_varraw = 881;
	int LITERAL_varcharc = 882;
	int LITERAL_varrawc = 883;
	int LITERAL_date_format = 884;
	int LITERAL_timezone = 885;
	int LITERAL_mask = 886;
	int LITERAL_location = 887;
	int LITERAL_aggregate = 888;
	int LITERAL_ldtrim = 889;
}
