--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

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
-- Name: sightings; Type: TABLE; Schema: public; Owner: jensensc
--

CREATE TABLE sightings (
    id integer NOT NULL,
    location character varying,
    "time" timestamp without time zone,
    animal_id integer,
    viewer_id integer
);


ALTER TABLE sightings OWNER TO jensensc;

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: jensensc
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_id_seq OWNER TO jensensc;

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jensensc
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: viewers; Type: TABLE; Schema: public; Owner: jensensc
--

CREATE TABLE viewers (
    id integer NOT NULL,
    ranger boolean,
    name character varying,
    phone character varying,
    ranger_number integer
);


ALTER TABLE viewers OWNER TO jensensc;

--
-- Name: viewers_id_seq; Type: SEQUENCE; Schema: public; Owner: jensensc
--

CREATE SEQUENCE viewers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE viewers_id_seq OWNER TO jensensc;

--
-- Name: viewers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jensensc
--

ALTER SEQUENCE viewers_id_seq OWNED BY viewers.id;


--
-- Name: wildlife_animals; Type: TABLE; Schema: public; Owner: jensensc
--

CREATE TABLE wildlife_animals (
    id integer NOT NULL,
    name character varying,
    health character varying,
    age character varying,
    color character varying,
    description character varying,
    gender character varying,
    endangered boolean
);


ALTER TABLE wildlife_animals OWNER TO jensensc;

--
-- Name: wildlife_animals_id_seq; Type: SEQUENCE; Schema: public; Owner: jensensc
--

CREATE SEQUENCE wildlife_animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wildlife_animals_id_seq OWNER TO jensensc;

--
-- Name: wildlife_animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jensensc
--

ALTER SEQUENCE wildlife_animals_id_seq OWNED BY wildlife_animals.id;


--
-- Name: sightings id; Type: DEFAULT; Schema: public; Owner: jensensc
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Name: viewers id; Type: DEFAULT; Schema: public; Owner: jensensc
--

ALTER TABLE ONLY viewers ALTER COLUMN id SET DEFAULT nextval('viewers_id_seq'::regclass);


--
-- Name: wildlife_animals id; Type: DEFAULT; Schema: public; Owner: jensensc
--

ALTER TABLE ONLY wildlife_animals ALTER COLUMN id SET DEFAULT nextval('wildlife_animals_id_seq'::regclass);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: jensensc
--

COPY sightings (id, location, "time", animal_id, viewer_id) FROM stdin;
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jensensc
--

SELECT pg_catalog.setval('sightings_id_seq', 27, true);


--
-- Data for Name: viewers; Type: TABLE DATA; Schema: public; Owner: jensensc
--

COPY viewers (id, ranger, name, phone, ranger_number) FROM stdin;
\.


--
-- Name: viewers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jensensc
--

SELECT pg_catalog.setval('viewers_id_seq', 78, true);


--
-- Data for Name: wildlife_animals; Type: TABLE DATA; Schema: public; Owner: jensensc
--

COPY wildlife_animals (id, name, health, age, color, description, gender, endangered) FROM stdin;
\.


--
-- Name: wildlife_animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jensensc
--

SELECT pg_catalog.setval('wildlife_animals_id_seq', 15, true);


--
-- Name: sightings sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: jensensc
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- Name: viewers viewers_pkey; Type: CONSTRAINT; Schema: public; Owner: jensensc
--

ALTER TABLE ONLY viewers
    ADD CONSTRAINT viewers_pkey PRIMARY KEY (id);


--
-- Name: wildlife_animals wildlife_animals_pkey; Type: CONSTRAINT; Schema: public; Owner: jensensc
--

ALTER TABLE ONLY wildlife_animals
    ADD CONSTRAINT wildlife_animals_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

