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
		keywords.put("using", 400);
		keywords.put("error_index", 642);
		keywords.put("referencing", 532);
		keywords.put("store", 561);
		keywords.put("fipsflag", 618);
		keywords.put("external", 573);
		keywords.put("wait", 765);
		keywords.put("pctfree", 552);
		keywords.put("float", 673);
		keywords.put("lrtrim", 802);
		keywords.put("missing", 800);
		keywords.put("references", 415);
		keywords.put("time", 658);
		keywords.put("over", 725);
		keywords.put("characterset", 780);
		keywords.put("movement", 412);
		keywords.put("role", 379);
		keywords.put("logon", 516);
		keywords.put("right", 748);
		keywords.put("else", 646);
		keywords.put("host", 439);
		keywords.put("monitoring", 556);
		keywords.put("savepoint", 772);
		keywords.put("number", 649);
		keywords.put("extract", 719);
		keywords.put("nocompress", 551);
		keywords.put("diassociate", 522);
		keywords.put("sysdate", 406);
		keywords.put("novalidate", 569);
		keywords.put("subtype", 621);
		keywords.put("each", 531);
		keywords.put("view", 365);
		keywords.put("big", 781);
		keywords.put("serially_reusable", 690);
		keywords.put("nextval", 723);
		keywords.put("unique", 537);
		keywords.put("direct_load", 549);
		keywords.put("raise", 627);
		keywords.put("exclusive", 771);
		keywords.put("before", 512);
		keywords.put("sqlerrm", 711);
		keywords.put("noguarantee", 473);
		keywords.put("instances", 575);
		keywords.put("nowait", 764);
		keywords.put("preprocessor", 821);
		keywords.put("write", 485);
		keywords.put("loop", 622);
		keywords.put("current", 704);
		keywords.put("left", 747);
		keywords.put("shutdown", 514);
		keywords.put("member", 709);
		keywords.put("sid", 746);
		keywords.put("define", 436);
		keywords.put("restrict_references", 615);
		keywords.put("partitions", 568);
		keywords.put("integer", 669);
		keywords.put("join", 751);
		keywords.put("hour", 721);
		keywords.put("operator", 377);
		keywords.put("analyze", 518);
		keywords.put("ref", 604);
		keywords.put("new", 535);
		keywords.put("including", 487);
		keywords.put("sequence", 372);
		keywords.put("library", 381);
		keywords.put("rem", 438);
		keywords.put("exists", 705);
		keywords.put("having", 760);
		keywords.put("public", 375);
		keywords.put("zone", 659);
		keywords.put("sizes", 787);
		keywords.put("body", 360);
		keywords.put("visible", 598);
		keywords.put("drop", 362);
		keywords.put("normal", 481);
		keywords.put("exception", 689);
		keywords.put("lead", 716);
		keywords.put("by", 505);
		keywords.put("long", 420);
		keywords.put("close", 633);
		keywords.put("any", 722);
		keywords.put("nobadfile", 790);
		keywords.put("key", 414);
		keywords.put("execute", 426);
		keywords.put("packages", 390);
		keywords.put("oserror", 429);
		keywords.put("double", 671);
		keywords.put("repheader", 446);
		keywords.put("and", 489);
		keywords.put("compress", 547);
		keywords.put("delimited", 778);
		keywords.put("overflow", 566);
		keywords.put("autonomous_transaction", 647);
		keywords.put("column", 388);
		keywords.put("day", 662);
		keywords.put("collect", 740);
		keywords.put("update", 530);
		keywords.put("raw", 655);
		keywords.put("connect", 509);
		keywords.put("nologging", 477);
		keywords.put("timezone_hour", 730);
		keywords.put("set", 419);
		keywords.put("var", 422);
		keywords.put("data_cache", 798);
		keywords.put("ddl", 521);
		keywords.put("statistics", 386);
		keywords.put("organization", 570);
		keywords.put("lag", 715);
		keywords.put("indextypes", 393);
		keywords.put("name", 693);
		keywords.put("disable", 409);
		keywords.put("types", 391);
		keywords.put("trim", 713);
		keywords.put("all", 548);
		keywords.put("parallel", 544);
		keywords.put("nodiscardfile", 792);
		keywords.put("constant", 620);
		keywords.put("precision", 672);
		keywords.put("oracle_loader", 835);
		keywords.put("uniform", 469);
		keywords.put("at", 703);
		keywords.put("as", 534);
		keywords.put("audit", 519);
		keywords.put("flush", 743);
		keywords.put("cascade", 366);
		keywords.put("off", 442);
		keywords.put("java", 692);
		keywords.put("disabled", 824);
		keywords.put("multiset", 714);
		keywords.put("enclosed", 808);
		keywords.put("no", 478);
		keywords.put("nocache", 503);
		keywords.put("package", 359);
		keywords.put("fixed", 779);
		keywords.put("of", 527);
		keywords.put("rank", 717);
		keywords.put("byte", 654);
		keywords.put("only", 484);
		keywords.put("on", 404);
		keywords.put("reset", 745);
		keywords.put("purge", 364);
		keywords.put("**", 702);
		keywords.put("limit", 577);
		keywords.put("increment", 504);
		keywords.put("coalesce", 496);
		keywords.put("final", 601);
		keywords.put("fetch", 634);
		keywords.put("or", 452);
		keywords.put("varrawc", 812);
		keywords.put("startup", 513);
		keywords.put("row", 411);
		keywords.put("managed", 395);
		keywords.put("newline", 777);
		keywords.put("noorder", 507);
		keywords.put("endian", 783);
		keywords.put("session", 742);
		keywords.put("then", 645);
		keywords.put("month", 661);
		keywords.put("records", 774);
		keywords.put("logoff", 517);
		keywords.put("comment", 403);
		keywords.put("interval", 560);
		keywords.put("sqlcode", 710);
		keywords.put("merge", 630);
		keywords.put("constraint", 417);
		keywords.put("pctthreshold", 571);
		keywords.put("quit", 440);
		keywords.put("rownum", 758);
		keywords.put("autoextend", 474);
		keywords.put("zoned", 816);
		keywords.put("null", 401);
		keywords.put("backup", 495);
		keywords.put("rowcount", 638);
		keywords.put("true", 700);
		keywords.put("ldtrim", 806);
		keywords.put("sql", 635);
		keywords.put("discardfile", 793);
		keywords.put("force", 371);
		keywords.put("insert", 529);
		keywords.put("timezone_region", 732);
		keywords.put("last", 763);
		keywords.put("count", 644);
		keywords.put("second", 663);
		keywords.put("save", 697);
		keywords.put("location", 818);
		keywords.put("char", 653);
		keywords.put("where", 753);
		keywords.put("type", 373);
		keywords.put("authid", 612);
		keywords.put("prior", 648);
		keywords.put("revoke", 524);
		keywords.put("maxextents", 581);
		keywords.put("partition", 558);
		keywords.put("spool", 441);
		keywords.put("when", 536);
		keywords.put("primary", 413);
		keywords.put("action", 593);
		keywords.put("none", 434);
		keywords.put("returning", 767);
		keywords.put("cycle", 500);
		keywords.put("minvalue", 499);
		keywords.put("pctused", 553);
		keywords.put("minus", 738);
		keywords.put("trailing", 755);
		keywords.put("int", 668);
		keywords.put("datafile", 461);
		keywords.put("error_code", 643);
		keywords.put("rows", 542);
		keywords.put("permanent", 486);
		keywords.put("intersect", 737);
		keywords.put("datafiles", 490);
		keywords.put("nomonitoring", 557);
		keywords.put("serveroutput", 447);
		keywords.put("readsize", 796);
		keywords.put("nosort", 596);
		keywords.put("rollback", 433);
		keywords.put("from", 729);
		keywords.put("add", 491);
		keywords.put("online", 479);
		keywords.put("while", 623);
		keywords.put("real", 665);
		keywords.put("extent", 466);
		keywords.put("if", 625);
		keywords.put("retention", 471);
		keywords.put("read", 483);
		keywords.put("compute", 543);
		keywords.put("less", 564);
		keywords.put("between", 708);
		keywords.put("is", 405);
		keywords.put("reuse", 465);
		keywords.put("rtrim", 805);
		keywords.put("rowtype", 607);
		keywords.put("into", 741);
		keywords.put("modify", 594);
		keywords.put("interface", 616);
		keywords.put("concat", 819);
		keywords.put("in", 562);
		keywords.put("database", 382);
		keywords.put("systimestamp", 407);
		keywords.put("local", 468);
		keywords.put("found", 636);
		keywords.put("varraw", 810);
		keywords.put("matched", 766);
		keywords.put("varray", 608);
		keywords.put("nulls", 761);
		keywords.put("optimal", 586);
		keywords.put("validate", 374);
		keywords.put("associate", 385);
		keywords.put("schema", 525);
		keywords.put("buffer_pool", 587);
		keywords.put("freelists", 583);
		keywords.put("boolean", 656);
		keywords.put("year", 660);
		keywords.put("pipelined", 694);
		keywords.put("option", 609);
		keywords.put("nvarchar", 677);
		keywords.put("contents", 488);
		keywords.put("whitespace", 831);
		keywords.put("constraints", 367);
		keywords.put("under", 600);
		keywords.put("charset", 688);
		keywords.put("optionally", 832);
		keywords.put("declare", 449);
		keywords.put("pragma", 614);
		keywords.put("dbtimezone", 727);
		keywords.put("system", 394);
		keywords.put("leading", 754);
		keywords.put("view_column_def_$internal$", 610);
		keywords.put("pls_integer", 670);
		keywords.put("load", 789);
		keywords.put("pctincrease", 582);
		keywords.put("exception_init", 619);
		keywords.put("desc", 540);
		keywords.put("sort", 595);
		keywords.put("minimum", 497);
		keywords.put("oracle_datapump", 836);
		keywords.put("next", 462);
		keywords.put("clob", 682);
		keywords.put("data", 828);
		keywords.put("repfooter", 445);
		keywords.put("unlimited", 578);
		keywords.put("oracle_number", 813);
		keywords.put("date", 657);
		keywords.put("bfile", 684);
		keywords.put("timestamp", 611);
		keywords.put("elsif", 735);
		keywords.put("builtin", 617);
		keywords.put("whenever", 427);
		keywords.put("parallel_enable", 695);
		keywords.put("procedure", 369);
		keywords.put("varchar", 675);
		keywords.put("reverse", 597);
		keywords.put("binary_integer", 650);
		keywords.put("currval", 724);
		keywords.put("varchar2", 676);
		keywords.put("bulk", 739);
		keywords.put("alter", 361);
		keywords.put("field", 801);
		keywords.put("replace", 453);
		keywords.put("oracle_date", 814);
		keywords.put("noparallel", 545);
		keywords.put("string", 786);
		keywords.put("prompt", 437);
		keywords.put("variable", 423);
		keywords.put("keep", 588);
		keywords.put("shared_pool", 744);
		keywords.put("to", 493);
		keywords.put("col", 424);
		keywords.put("both", 756);
		keywords.put("inner", 749);
		keywords.put("synonym", 376);
		keywords.put("nologfile", 794);
		keywords.put("identified", 510);
		keywords.put("after", 511);
		keywords.put("timezone", 833);
		keywords.put("values", 563);
		keywords.put("nclob", 683);
		keywords.put("commit", 432);
		keywords.put("encrypt", 590);
		keywords.put("sessiontimezone", 726);
		keywords.put("ldrtrim", 840);
		keywords.put("parameters", 775);
		keywords.put("fields", 799);
		keywords.put("enabled", 823);
		keywords.put("index", 370);
		keywords.put("bitmap", 538);
		keywords.put("sta", 443);
		keywords.put("timezone_minute", 731);
		keywords.put("select", 629);
		keywords.put("maxvalue", 498);
		keywords.put("indexes", 392);
		keywords.put("cost", 398);
		keywords.put("size", 464);
		keywords.put("cast", 712);
		keywords.put("than", 565);
		keywords.put("exec", 425);
		keywords.put("case", 628);
		keywords.put("foreign", 591);
		keywords.put("natural", 651);
		keywords.put("freelist", 584);
		keywords.put("timezone_abbr", 733);
		keywords.put("maxsize", 463);
		keywords.put("tempfile", 459);
		keywords.put("compatible", 827);
		keywords.put("date_format", 809);
		keywords.put("novisible", 599);
		keywords.put("bulk_exceptions", 641);
		keywords.put("logfile", 795);
		keywords.put("lobfile", 820);
		keywords.put("functions", 389);
		keywords.put("filesystem_like_logging", 546);
		keywords.put("nocopy", 686);
		keywords.put("immediate", 482);
		keywords.put("counted", 815);
		keywords.put("operations", 550);
		keywords.put("latest", 837);
		keywords.put("smallint", 664);
		keywords.put("selectivity", 399);
		keywords.put("out", 685);
		keywords.put("smallfile", 457);
		keywords.put("aggregate", 773);
		keywords.put("cursor", 605);
		keywords.put("wrapped", 613);
		keywords.put("numeric", 666);
		keywords.put("for", 508);
		keywords.put("distinct", 728);
		keywords.put("open", 632);
		keywords.put("are", 839);
		keywords.put("initial", 579);
		keywords.put("noaudit", 520);
		keywords.put("maxtrans", 555);
		keywords.put("any_cs", 687);
		keywords.put("false", 701);
		keywords.put("compression", 822);
		keywords.put("groups", 585);
		keywords.put("table", 363);
		keywords.put("like", 706);
		keywords.put("create", 451);
		keywords.put("exit", 430);
		keywords.put("not", 408);
		keywords.put("record", 603);
		keywords.put("recycle", 589);
		keywords.put("asc", 539);
		keywords.put("start", 444);
		keywords.put("truncate", 402);
		keywords.put("indices", 699);
		keywords.put("language", 691);
		keywords.put("position", 807);
		keywords.put("notrim", 803);
		keywords.put("bytes", 788);
		keywords.put("range", 559);
		keywords.put("initrans", 554);
		keywords.put("link", 383);
		keywords.put("goto", 626);
		keywords.put("nocheck", 785);
		keywords.put("offline", 480);
		keywords.put("escape", 707);
		keywords.put("version", 826);
		keywords.put("mode", 769);
		keywords.put("character", 679);
		keywords.put("badfile", 791);
		keywords.put("exceptions", 698);
		keywords.put("def", 435);
		keywords.put("union", 736);
		keywords.put("delete", 528);
		keywords.put("bulk_rowcount", 640);
		keywords.put("deterministic", 696);
		keywords.put("end", 494);
		keywords.put("trigger", 384);
		keywords.put("isopen", 639);
		keywords.put("rely", 416);
		keywords.put("cache", 502);
		keywords.put("return", 606);
		keywords.put("dictionary", 470);
		keywords.put("bigfile", 456);
		keywords.put("unsigned", 817);
		keywords.put("access", 776);
		keywords.put("transforms", 834);
		keywords.put("current_timestamp", 757);
		keywords.put("degree", 574);
		keywords.put("directory", 380);
		keywords.put("terminated", 830);
		keywords.put("old", 533);
		keywords.put("minextents", 580);
		keywords.put("grant", 523);
		keywords.put("transaction", 768);
		keywords.put("undo", 460);
		keywords.put("nvarchar2", 678);
		keywords.put("rename", 492);
		keywords.put("enable", 410);
		keywords.put("mask", 829);
		keywords.put("little", 782);
		keywords.put("show", 421);
		keywords.put("skip", 797);
		keywords.put("preserve", 541);
		keywords.put("function", 368);
		keywords.put("heap", 572);
		keywords.put("tablespace", 458);
		keywords.put("encryption", 825);
		keywords.put("positive", 652);
		keywords.put("work", 734);
		keywords.put("hash", 567);
		keywords.put("varcharc", 811);
		keywords.put("global", 454);
		keywords.put("mark", 784);
		keywords.put("logging", 476);
		keywords.put("forall", 624);
		keywords.put("restrict", 592);
		keywords.put("default", 397);
		keywords.put("management", 467);
		keywords.put("dense_rank", 718);
		keywords.put("characters", 838);
		keywords.put("reject", 576);
		keywords.put("temporary", 455);
		keywords.put("servererror", 515);
		keywords.put("object", 602);
		keywords.put("minute", 720);
		keywords.put("share", 770);
		keywords.put("order", 506);
		keywords.put("full", 752);
		keywords.put("ltrim", 804);
		keywords.put("notfound", 637);
		keywords.put("with", 387);
		keywords.put("check", 418);
		keywords.put("lock", 631);
		keywords.put("the", 759);
		keywords.put("sqlerror", 428);
		keywords.put("decimal", 674);
		keywords.put("begin", 448);
		keywords.put("blob", 681);
		keywords.put("nocycle", 501);
		keywords.put("outer", 750);
		keywords.put("continue", 431);
		keywords.put("instead", 526);
		keywords.put("guarantee", 472);
		keywords.put("group", 475);
		keywords.put("first", 762);
		keywords.put("user", 378);
		keywords.put("rowid", 680);
		keywords.put("storage", 396);
	}
}
