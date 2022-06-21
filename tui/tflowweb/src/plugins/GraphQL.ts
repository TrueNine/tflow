import {ApolloClient, createHttpLink, InMemoryCache} from "@apollo/client";
import Vue from "vue";
import {DefaultApolloClient} from "@vue/apollo-composable";

const httpLink = createHttpLink({
  uri: "http://localhost:8080/"
});
const cache = new InMemoryCache();
const client = new ApolloClient({link: httpLink, cache});
Vue.provide(DefaultApolloClient, client);
export default client;
