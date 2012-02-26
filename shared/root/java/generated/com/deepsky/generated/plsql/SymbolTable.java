package com.deepsky.generated.plsql;

import java.util.Map;
import java.util.HashMap;

public class SymbolTable {

	public static Integer get(String text){
		return keywords.get(text.toLowerCase());
	}

	static public Map<String, Integer> keywords;
	static {
		keywords = new HashMap<String, Integer>();
		keywords.put("using", 427);
		keywords.put("error_index", 691);
		keywords.put("referencing", 571);
		keywords.put("nested", 580);
		keywords.put("store", 581);
		keywords.put("fipsflag", 668);
		keywords.put("external", 623);
		keywords.put("privileges", 788);
		keywords.put("wait", 815);
		keywords.put("pctfree", 602);
		keywords.put("float", 721);
		keywords.put("lrtrim", 871);
		keywords.put("missing", 861);
		keywords.put("over", 774);
		keywords.put("references", 441);
		keywords.put("time", 707);
		keywords.put("characterset", 840);
		keywords.put("movement", 437);
		keywords.put("role", 406);
		keywords.put("logon", 555);
		keywords.put("right", 801);
		keywords.put("else", 695);
		keywords.put("host", 467);
		keywords.put("monitoring", 609);
		keywords.put("savepoint", 822);
		keywords.put("number", 698);
		keywords.put("extract", 768);
		keywords.put("nocompress", 601);
		keywords.put("diassociate", 561);
		keywords.put("sysdate", 445);
		keywords.put("novalidate", 620);
		keywords.put("subtype", 671);
		keywords.put("each", 570);
		keywords.put("view", 392);
		keywords.put("big", 842);
		keywords.put("serially_reusable", 738);
		keywords.put("nextval", 772);
		keywords.put("unique", 444);
		keywords.put("direct_load", 599);
		keywords.put("raise", 677);
		keywords.put("exclusive", 821);
		keywords.put("before", 551);
		keywords.put("sqlerrm", 759);
		keywords.put("noguarantee", 514);
		keywords.put("globally", 488);
		keywords.put("instances", 625);
		keywords.put("nowait", 814);
		keywords.put("preprocessor", 859);
		keywords.put("pctversion", 588);
		keywords.put("write", 526);
		keywords.put("loop", 672);
		keywords.put("current", 752);
		keywords.put("left", 800);
		keywords.put("shutdown", 553);
		keywords.put("member", 757);
		keywords.put("sid", 799);
		keywords.put("define", 464);
		keywords.put("restrict_references", 665);
		keywords.put("partitions", 619);
		keywords.put("integer", 717);
		keywords.put("join", 804);
		keywords.put("hour", 770);
		keywords.put("operator", 404);
		keywords.put("analyze", 557);
		keywords.put("ref", 655);
		keywords.put("new", 573);
		keywords.put("including", 528);
		keywords.put("sequence", 399);
		keywords.put("library", 408);
		keywords.put("rem", 466);
		keywords.put("exists", 753);
		keywords.put("having", 810);
		keywords.put("public", 402);
		keywords.put("zone", 708);
		keywords.put("sizes", 848);
		keywords.put("body", 387);
		keywords.put("visible", 649);
		keywords.put("drop", 389);
		keywords.put("normal", 522);
		keywords.put("exception", 737);
		keywords.put("lead", 765);
		keywords.put("by", 485);
		keywords.put("long", 448);
		keywords.put("close", 682);
		keywords.put("any", 771);
		keywords.put("nobadfile", 852);
		keywords.put("key", 440);
		keywords.put("execute", 454);
		keywords.put("packages", 417);
		keywords.put("oserror", 457);
		keywords.put("double", 719);
		keywords.put("repheader", 474);
		keywords.put("and", 530);
		keywords.put("compress", 597);
		keywords.put("delimited", 838);
		keywords.put("overflow", 611);
		keywords.put("autonomous_transaction", 696);
		keywords.put("column", 415);
		keywords.put("day", 711);
		keywords.put("collect", 793);
		keywords.put("update", 569);
		keywords.put("raw", 704);
		keywords.put("connect", 549);
		keywords.put("timezone_hour", 779);
		keywords.put("nologging", 518);
		keywords.put("set", 447);
		keywords.put("var", 450);
		keywords.put("data_cache", 857);
		keywords.put("ddl", 560);
		keywords.put("statistics", 413);
		keywords.put("organization", 621);
		keywords.put("lag", 764);
		keywords.put("indextypes", 420);
		keywords.put("name", 744);
		keywords.put("disable", 434);
		keywords.put("types", 418);
		keywords.put("trim", 762);
		keywords.put("all", 598);
		keywords.put("parallel", 607);
		keywords.put("nodiscardfile", 854);
		keywords.put("constant", 670);
		keywords.put("precision", 720);
		keywords.put("oracle_loader", 823);
		keywords.put("uniform", 510);
		keywords.put("at", 751);
		keywords.put("audit", 558);
		keywords.put("as", 487);
		keywords.put("cell_flash_cache", 640);
		keywords.put("flush", 796);
		keywords.put("cascade", 393);
		keywords.put("off", 473);
		keywords.put("java", 743);
		keywords.put("disabled", 834);
		keywords.put("multiset", 763);
		keywords.put("enclosed", 867);
		keywords.put("no", 519);
		keywords.put("nocache", 544);
		keywords.put("package", 386);
		keywords.put("fixed", 837);
		keywords.put("of", 566);
		keywords.put("rank", 766);
		keywords.put("byte", 703);
		keywords.put("reset", 798);
		keywords.put("only", 525);
		keywords.put("on", 431);
		keywords.put("purge", 391);
		keywords.put("**", 750);
		keywords.put("limit", 627);
		keywords.put("increment", 545);
		keywords.put("coalesce", 537);
		keywords.put("final", 652);
		keywords.put("fetch", 683);
		keywords.put("or", 480);
		keywords.put("varrawc", 884);
		keywords.put("startup", 552);
		keywords.put("row", 436);
		keywords.put("managed", 422);
		keywords.put("newline", 839);
		keywords.put("noorder", 547);
		keywords.put("endian", 844);
		keywords.put("session", 795);
		keywords.put("then", 694);
		keywords.put("month", 710);
		keywords.put("records", 836);
		keywords.put("logoff", 556);
		keywords.put("comment", 430);
		keywords.put("creation", 592);
		keywords.put("interval", 614);
		keywords.put("sqlcode", 758);
		keywords.put("externally", 486);
		keywords.put("merge", 680);
		keywords.put("constraint", 438);
		keywords.put("pctthreshold", 595);
		keywords.put("quit", 468);
		keywords.put("rownum", 808);
		keywords.put("autoextend", 515);
		keywords.put("zoned", 878);
		keywords.put("null", 428);
		keywords.put("backup", 536);
		keywords.put("rowcount", 687);
		keywords.put("true", 748);
		keywords.put("ldtrim", 890);
		keywords.put("sql", 684);
		keywords.put("discardfile", 855);
		keywords.put("account", 495);
		keywords.put("force", 398);
		keywords.put("insert", 568);
		keywords.put("timezone_region", 781);
		keywords.put("last", 813);
		keywords.put("count", 693);
		keywords.put("second", 712);
		keywords.put("save", 745);
		keywords.put("location", 888);
		keywords.put("char", 702);
		keywords.put("segment", 591);
		keywords.put("where", 806);
		keywords.put("type", 400);
		keywords.put("authid", 662);
		keywords.put("prior", 697);
		keywords.put("revoke", 563);
		keywords.put("maxextents", 630);
		keywords.put("partition", 612);
		keywords.put("spool", 469);
		keywords.put("when", 574);
		keywords.put("primary", 439);
		keywords.put("value", 584);
		keywords.put("action", 644);
		keywords.put("none", 462);
		keywords.put("freepools", 589);
		keywords.put("returning", 817);
		keywords.put("cycle", 541);
		keywords.put("minvalue", 540);
		keywords.put("pctused", 603);
		keywords.put("minus", 791);
		keywords.put("trailing", 784);
		keywords.put("int", 716);
		keywords.put("datafile", 502);
		keywords.put("error_code", 692);
		keywords.put("rows", 579);
		keywords.put("permanent", 527);
		keywords.put("intersect", 790);
		keywords.put("datafiles", 531);
		keywords.put("nomonitoring", 610);
		keywords.put("serveroutput", 475);
		keywords.put("readsize", 856);
		keywords.put("nosort", 647);
		keywords.put("rollback", 461);
		keywords.put("from", 778);
		keywords.put("add", 532);
		keywords.put("online", 520);
		keywords.put("while", 673);
		keywords.put("real", 714);
		keywords.put("extent", 507);
		keywords.put("if", 675);
		keywords.put("retention", 512);
		keywords.put("read", 524);
		keywords.put("compute", 606);
		keywords.put("less", 616);
		keywords.put("between", 756);
		keywords.put("is", 432);
		keywords.put("reuse", 506);
		keywords.put("rtrim", 874);
		keywords.put("rowtype", 657);
		keywords.put("into", 794);
		keywords.put("modify", 645);
		keywords.put("interface", 666);
		keywords.put("concat", 865);
		keywords.put("in", 590);
		keywords.put("database", 409);
		keywords.put("systimestamp", 446);
		keywords.put("local", 509);
		keywords.put("found", 685);
		keywords.put("varraw", 882);
		keywords.put("matched", 816);
		keywords.put("varray", 658);
		keywords.put("nulls", 811);
		keywords.put("optimal", 635);
		keywords.put("validate", 401);
		keywords.put("associate", 412);
		keywords.put("schema", 564);
		keywords.put("buffer_pool", 636);
		keywords.put("freelists", 632);
		keywords.put("boolean", 705);
		keywords.put("year", 709);
		keywords.put("pipelined", 739);
		keywords.put("option", 659);
		keywords.put("nvarchar", 725);
		keywords.put("contents", 529);
		keywords.put("whitespace", 869);
		keywords.put("constraints", 394);
		keywords.put("under", 651);
		keywords.put("charset", 736);
		keywords.put("optionally", 870);
		keywords.put("declare", 477);
		keywords.put("pragma", 664);
		keywords.put("reads", 587);
		keywords.put("dbtimezone", 776);
		keywords.put("system", 421);
		keywords.put("decode", 761);
		keywords.put("leading", 783);
		keywords.put("view_column_def_$internal$", 660);
		keywords.put("pls_integer", 718);
		keywords.put("load", 851);
		keywords.put("pctincrease", 631);
		keywords.put("exception_init", 669);
		keywords.put("desc", 577);
		keywords.put("sort", 646);
		keywords.put("minimum", 538);
		keywords.put("oracle_datapump", 824);
		keywords.put("next", 503);
		keywords.put("clob", 730);
		keywords.put("data", 841);
		keywords.put("repfooter", 472);
		keywords.put("unlimited", 491);
		keywords.put("deferred", 593);
		keywords.put("oracle_number", 880);
		keywords.put("date", 706);
		keywords.put("bfile", 732);
		keywords.put("timestamp", 661);
		keywords.put("lob", 585);
		keywords.put("elsif", 787);
		keywords.put("builtin", 667);
		keywords.put("whenever", 455);
		keywords.put("parallel_enable", 740);
		keywords.put("procedure", 396);
		keywords.put("varchar", 723);
		keywords.put("reverse", 648);
		keywords.put("binary_integer", 699);
		keywords.put("flash_cache", 639);
		keywords.put("currval", 773);
		keywords.put("varchar2", 724);
		keywords.put("bulk", 792);
		keywords.put("alter", 388);
		keywords.put("field", 862);
		keywords.put("replace", 481);
		keywords.put("oracle_date", 879);
		keywords.put("noparallel", 608);
		keywords.put("string", 847);
		keywords.put("prompt", 465);
		keywords.put("variable", 451);
		keywords.put("shared_pool", 797);
		keywords.put("keep", 637);
		keywords.put("to", 534);
		keywords.put("col", 452);
		keywords.put("both", 785);
		keywords.put("inner", 802);
		keywords.put("synonym", 403);
		keywords.put("nologfile", 827);
		keywords.put("identified", 484);
		keywords.put("after", 550);
		keywords.put("timezone", 886);
		keywords.put("values", 615);
		keywords.put("nclob", 731);
		keywords.put("commit", 460);
		keywords.put("encrypt", 641);
		keywords.put("sessiontimezone", 775);
		keywords.put("ldrtrim", 875);
		keywords.put("parameters", 826);
		keywords.put("fields", 860);
		keywords.put("enabled", 833);
		keywords.put("index", 397);
		keywords.put("bitmap", 575);
		keywords.put("sta", 470);
		keywords.put("timezone_minute", 780);
		keywords.put("select", 679);
		keywords.put("maxvalue", 539);
		keywords.put("indexes", 419);
		keywords.put("cost", 425);
		keywords.put("size", 505);
		keywords.put("cast", 760);
		keywords.put("than", 617);
		keywords.put("exec", 453);
		keywords.put("case", 678);
		keywords.put("foreign", 642);
		keywords.put("natural", 700);
		keywords.put("freelist", 633);
		keywords.put("timezone_abbr", 782);
		keywords.put("maxsize", 504);
		keywords.put("tempfile", 500);
		keywords.put("compatible", 830);
		keywords.put("date_format", 885);
		keywords.put("novisible", 650);
		keywords.put("bulk_exceptions", 690);
		keywords.put("logfile", 828);
		keywords.put("lobfile", 866);
		keywords.put("profile", 492);
		keywords.put("functions", 416);
		keywords.put("filesystem_like_logging", 596);
		keywords.put("nocopy", 734);
		keywords.put("immediate", 523);
		keywords.put("counted", 881);
		keywords.put("operations", 600);
		keywords.put("latest", 831);
		keywords.put("smallint", 713);
		keywords.put("selectivity", 426);
		keywords.put("out", 733);
		keywords.put("smallfile", 499);
		keywords.put("aggregate", 889);
		keywords.put("chunk", 586);
		keywords.put("cursor", 656);
		keywords.put("wrapped", 663);
		keywords.put("numeric", 715);
		keywords.put("for", 548);
		keywords.put("distinct", 777);
		keywords.put("open", 681);
		keywords.put("are", 863);
		keywords.put("initial", 628);
		keywords.put("noaudit", 559);
		keywords.put("maxtrans", 605);
		keywords.put("any_cs", 735);
		keywords.put("false", 749);
		keywords.put("compression", 832);
		keywords.put("groups", 634);
		keywords.put("unlock", 497);
		keywords.put("table", 390);
		keywords.put("like", 754);
		keywords.put("create", 479);
		keywords.put("exit", 458);
		keywords.put("not", 433);
		keywords.put("record", 654);
		keywords.put("recycle", 638);
		keywords.put("asc", 576);
		keywords.put("start", 471);
		keywords.put("truncate", 429);
		keywords.put("indices", 747);
		keywords.put("language", 742);
		keywords.put("position", 876);
		keywords.put("notrim", 872);
		keywords.put("bytes", 849);
		keywords.put("range", 613);
		keywords.put("initrans", 604);
		keywords.put("link", 410);
		keywords.put("goto", 676);
		keywords.put("nocheck", 846);
		keywords.put("offline", 521);
		keywords.put("escape", 755);
		keywords.put("version", 829);
		keywords.put("mode", 819);
		keywords.put("character", 727);
		keywords.put("badfile", 853);
		keywords.put("exceptions", 746);
		keywords.put("def", 463);
		keywords.put("union", 789);
		keywords.put("delete", 567);
		keywords.put("bulk_rowcount", 689);
		keywords.put("deterministic", 741);
		keywords.put("end", 535);
		keywords.put("expire", 494);
		keywords.put("trigger", 411);
		keywords.put("isopen", 688);
		keywords.put("rely", 442);
		keywords.put("cache", 543);
		keywords.put("return", 582);
		keywords.put("dictionary", 511);
		keywords.put("bigfile", 498);
		keywords.put("unsigned", 877);
		keywords.put("access", 825);
		keywords.put("transforms", 864);
		keywords.put("current_timestamp", 807);
		keywords.put("degree", 624);
		keywords.put("directory", 407);
		keywords.put("terminated", 868);
		keywords.put("old", 572);
		keywords.put("minextents", 629);
		keywords.put("grant", 562);
		keywords.put("transaction", 818);
		keywords.put("undo", 501);
		keywords.put("nvarchar2", 726);
		keywords.put("rename", 533);
		keywords.put("enable", 435);
		keywords.put("mask", 887);
		keywords.put("little", 843);
		keywords.put("show", 449);
		keywords.put("skip", 858);
		keywords.put("password", 493);
		keywords.put("preserve", 578);
		keywords.put("function", 395);
		keywords.put("heap", 622);
		keywords.put("tablespace", 489);
		keywords.put("encryption", 835);
		keywords.put("positive", 701);
		keywords.put("work", 786);
		keywords.put("locator", 583);
		keywords.put("hash", 618);
		keywords.put("varcharc", 883);
		keywords.put("global", 482);
		keywords.put("mark", 845);
		keywords.put("logging", 517);
		keywords.put("forall", 674);
		keywords.put("restrict", 643);
		keywords.put("default", 424);
		keywords.put("management", 508);
		keywords.put("dense_rank", 767);
		keywords.put("characters", 850);
		keywords.put("reject", 626);
		keywords.put("temporary", 483);
		keywords.put("servererror", 554);
		keywords.put("object", 653);
		keywords.put("minute", 769);
		keywords.put("share", 820);
		keywords.put("quota", 490);
		keywords.put("order", 546);
		keywords.put("full", 805);
		keywords.put("ltrim", 873);
		keywords.put("notfound", 686);
		keywords.put("with", 414);
		keywords.put("check", 443);
		keywords.put("lock", 496);
		keywords.put("the", 809);
		keywords.put("sqlerror", 456);
		keywords.put("decimal", 722);
		keywords.put("begin", 476);
		keywords.put("blob", 729);
		keywords.put("nocycle", 542);
		keywords.put("outer", 803);
		keywords.put("continue", 459);
		keywords.put("instead", 565);
		keywords.put("guarantee", 513);
		keywords.put("cluster", 594);
		keywords.put("group", 516);
		keywords.put("first", 812);
		keywords.put("user", 405);
		keywords.put("rowid", 728);
		keywords.put("storage", 423);
	}
}
