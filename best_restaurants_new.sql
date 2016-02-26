--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cuisine; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE cuisine (
    cuisineid integer NOT NULL,
    type character varying
);


ALTER TABLE cuisine OWNER TO "Guest";

--
-- Name: cuisine_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cuisine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cuisine_id_seq OWNER TO "Guest";

--
-- Name: cuisine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cuisine_id_seq OWNED BY cuisine.cuisineid;


--
-- Name: regions; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE regions (
    id integer NOT NULL,
    area character varying
);


ALTER TABLE regions OWNER TO "Guest";

--
-- Name: regions_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE regions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE regions_id_seq OWNER TO "Guest";

--
-- Name: regions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE regions_id_seq OWNED BY regions.id;


--
-- Name: restaurants; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE restaurants (
    id integer NOT NULL,
    name character varying,
    cuisineid integer,
    pricerange integer,
    address character varying,
    regionid integer,
    hours character varying
);


ALTER TABLE restaurants OWNER TO "Guest";

--
-- Name: restaurants_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE restaurants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurants_id_seq OWNER TO "Guest";

--
-- Name: restaurants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE restaurants_id_seq OWNED BY restaurants.id;


--
-- Name: cuisineid; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cuisine ALTER COLUMN cuisineid SET DEFAULT nextval('cuisine_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY regions ALTER COLUMN id SET DEFAULT nextval('regions_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY restaurants ALTER COLUMN id SET DEFAULT nextval('restaurants_id_seq'::regclass);


--
-- Data for Name: cuisine; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cuisine (cuisineid, type) FROM stdin;
3	sushi
5	sushi
1	Mexican
2	Italian
4	Southern
6	Burgers
7	Sushi
8	Swanky Pub Food
9	Mediterranean
\.


--
-- Name: cuisine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cuisine_id_seq', 9, true);


--
-- Data for Name: regions; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY regions (id, area) FROM stdin;
1	North Portland
2	Northeast Portland
3	Southeast Portland
4	Northwest Portland
5	Southwest Portland
6	Gresham
7	Beaverton
8	Lake Oswego
9	Vancouver
\.


--
-- Name: regions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('regions_id_seq', 9, true);


--
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY restaurants (id, name, cuisineid, pricerange, address, regionid, hours) FROM stdin;
5	Feral Pub	8	2	\N	9	\N
8	Pizza Hut	2	1	\N	3	24/7
9	Applebee's	6	2	\N	4	\N
2	Lardo	2	1	\N	4	24 hours a day
1	Screen Door	1	3	1200 Main St	1	4PM to 3AM
10	Mediterranean Grill	9	0	\N	\N	\N
3	Luca	4	5	\N	6	\N
11	Du's Grill	7	3	NE Sandy	2	8AM to 5PM
7	Uchu	7	2	\N	7	\N
\.


--
-- Name: restaurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('restaurants_id_seq', 11, true);


--
-- Name: cuisine_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY cuisine
    ADD CONSTRAINT cuisine_pkey PRIMARY KEY (cuisineid);


--
-- Name: regions_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY regions
    ADD CONSTRAINT regions_pkey PRIMARY KEY (id);


--
-- Name: restaurants_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY restaurants
    ADD CONSTRAINT restaurants_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

