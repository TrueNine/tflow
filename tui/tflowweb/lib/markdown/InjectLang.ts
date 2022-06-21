import hijs from "highlight.js";
import java from "highlight.js/lib/languages/java";
import ts from "highlight.js/lib/languages/typescript";
import js from "highlight.js/lib/languages/javascript";
import python from "highlight.js/lib/languages/python";
import c from "highlight.js/lib/languages/c";
import cpp from "highlight.js/lib/languages/cpp";

import sql from "highlight.js/lib/languages/sql";
import scss from "highlight.js/lib/languages/scss";
import css from "highlight.js/lib/languages/css";

import json from "highlight.js/lib/languages/json";
import properties from "highlight.js/lib/languages/properties";

import yaml from "highlight.js/lib/languages/yaml";
import xml from "highlight.js/lib/languages/xml";

const languages = [java, ts, js, python, c, cpp, sql, scss, css, json, yaml, xml, properties];

languages.forEach((lang) => hijs.registerLanguage(lang.name, lang));

export default hijs;
