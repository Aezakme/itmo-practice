--
-- PostgreSQL database dump
--

-- Dumped from database version 11.7 (Ubuntu 11.7-0ubuntu0.19.10.1)
-- Dumped by pg_dump version 11.7 (Ubuntu 11.7-0ubuntu0.19.10.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: availability; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.availability (
    id bigint NOT NULL,
    book_id bigint NOT NULL,
    office_id bigint NOT NULL,
    amount integer NOT NULL
);


ALTER TABLE public.availability OWNER TO postgres;

--
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    description text,
    year date
);


ALTER TABLE public.books OWNER TO postgres;

--
-- Name: offices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offices (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    address character varying(255) NOT NULL
);


ALTER TABLE public.offices OWNER TO postgres;

--
-- Data for Name: availability; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.availability (id, book_id, office_id, amount) FROM stdin;
\.


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books (id, title, author, description, year) FROM stdin;
\.


--
-- Data for Name: offices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.offices (id, name, address) FROM stdin;
\.


--
-- PostgreSQL database dump complete
--

