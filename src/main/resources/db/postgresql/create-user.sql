-- DANGER: this script creates a superuser.
CREATE USER panda WITH PASSWORD 'password'
	SUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	LOGIN
	NOREPLICATION
	BYPASSRLS
	CONNECTION LIMIT -1;