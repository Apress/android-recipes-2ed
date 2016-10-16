create table categories(id text, name_en text);
insert into categories(id, name_en) values('density', 'DENSITY');
insert into categories(id, name_en) values('energy', 'ENERGY AND WORK');

create table density(name_en text, multiplier real);
insert into density(name_en, multiplier) values("EARTH'S DENSITY (MEAN) > PSI/1000 FEET", 2392.204767079);
insert into density(name_en, multiplier) values("PSI/1000 FEET > EARTH'S DENSITY (MEAN)", 0.000418024);

create table energy(name_en text, multiplier real);
insert into energy(name_en, multiplier) values('WATT-HOURS > TONS (EXPLOSIVE)', 0.00000086);
insert into energy(name_en, multiplier) values('TONS (EXPLOSIVE) > WATT-HOURS', 1162222.2222222);