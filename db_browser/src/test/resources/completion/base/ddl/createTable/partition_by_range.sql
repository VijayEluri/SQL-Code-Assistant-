CREATE TABLE XDV_AAI_15M_INTAPA_FT (
    TAT_ID		NUMBER(22),
    TAC_ID		NUMBER(22),
    TRC_ID		NUMBER(22),
    TRS_ID		NUMBER(22),
    TRQ_ID		NUMBER(22),
    DDE_ID		NUMBER(22),
    DTE_ID		NUMBER(22),
    CONSTRAINT XDV_AAI_15M_TRC_FK
        FOREIGN KEY (TRC_ID)
        REFERENCES XDV_TRN_CAUSES_DT(ID)
    ,
    CONSTRAINT XDV_AAI_15M_ASV_FK
        FOREIGN KEY (ASV_ID)
        REFERENCES XDV_SRV_AGR_SERVER_DT(ID)
    ,
    CONSTRAINT XDV_AAI_15M_GSV_FK
        FOREIGN KEY (GSV_ID)
        REFERENCES XDV_SRV_GRP_SERVER_DT(ID)
    ,
    CONSTRAINT XDV_AAI_15M_GNW_FK
        FOREIGN KEY (GNW_ID)
        REFERENCES XDV_SUB_GRP_NETWORK_DT(ID)
    ,
    CONSTRAINT XDV_AAI_15M_TAT_FK
        FOREIGN KEY (TAT_ID)
        REFERENCES XDV_TRN_APP_TYPE_DT(ID)
    ,
    CONSTRAINT XDV_AAI_15M_TRQ_FK
        FOREIGN KEY (TRQ_ID)
        REFERENCES XDV_TRN_QOS_DT(ID)
    ,
    CONSTRAINT XDV_AAI_15M_TAC_FK
        FOREIGN KEY (TAC_ID)
        REFERENCES XDV_TRN_APP_CONTENT_TYPE_DT(ID)
    ,
    CONSTRAINT XDV_AAI_15M_DTZ_FK
        FOREIGN KEY (DTZ_ID)
        REFERENCES XDV_DTM_TIMEZONE_DT(ID)

)
NOLOGGING
PARTITION BY RANGE (<caret>)
(PARTITION
XDV_AAI_15M_20101024_2300_P VALUES LESS THAN (1287964800)
TABLESPACE XDV_AAI_15M_20101024_2300_TS
PCTFREE	0
INITRANS	1
MAXTRANS	255,
PARTITION
XDV_AAI_15M_20101025_0800_P VALUES LESS THAN (1287997200)
TABLESPACE XDV_AAI_15M_20101025_0800_TS
PCTFREE	0
INITRANS	1
MAXTRANS	255);