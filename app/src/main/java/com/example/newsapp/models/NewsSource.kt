package com.example.newsapp.models

enum class NewsSource(val imageUrl: String, val rssUrl: String, val politicalBias: PoliticalBias) {
    ALTERNET("", "https://www.alternet.org/feeds/feed.rss", PoliticalBias.LEFT),
    BUZZFEED("https://webappstatic.buzzfeed.com/static/images/public/rss/logo.png", "https://www.buzzfeed.com/index.xml", PoliticalBias.LEFT),
    JACOBIN("https://assets.pippa.io/shows/619be5c0705138001b9c8479/1654018052341-248e1190c65d64168828746f363fd55e.jpeg", "https://blubrry.com/feeds/jacobin.xml", PoliticalBias.LEFT),
    DEMOCRACY_NOW("https://www.democracynow.org/assets/dn-logo-for-podcast-7372130f672a82cfeb392d7de1c62fa4183649c2770d4426b11573f81cd104a9.png", "https://www.democracynow.org/democracynow.rss", PoliticalBias.CENTER_LEFT),
    TRUTH_OUT("https://truthout.org/wp-content/uploads/2018/03/cropped-Truthout-T-logo-sq-1200x1200-e1521532408148-32x32.png", "https://truthout.org/latest/feed/", PoliticalBias.CENTER_LEFT),
    THE_INTERCEPT("https://i1.feedspot.com/4477517.jpg?t=1603896254", "https://theintercept.com/feed/?lang=en", PoliticalBias.CENTER_LEFT),
    MOTHER_JONES("https://www.motherjones.com/wp-content/uploads/2017/09/cropped-favicon-512x512.png?w=56", "http://feeds.feedburner.com/motherjones/feed", PoliticalBias.CENTER_LEFT),
    RT("https://www.rt.com/static/img/logo-rss.png", "https://www.rt.com/rss/", PoliticalBias.CENTER_LEFT),
    AL_JAZEERA("https://www.aljazeera.com/images/logo_aje.png", "https://www.aljazeera.com/xml/rss/all.xml", PoliticalBias.CENTER_LEFT),
    THE_GUARDIAN("", "https://www.theguardian.com/news/series/todayinfocus/podcast.xml", PoliticalBias.CENTER_LEFT),
    PBS("https://upload.wikimedia.org/wikipedia/en/thumb/3/33/PBS_logo.svg/300px-PBS_logo.svg.png", "https://www.pbs.org/newshour/feeds/rss/headlines", PoliticalBias.CENTER_LEFT),
    VOX("https://cdn.vox-cdn.com/community_logos/52517/voxv.png", "https://www.vox.com/rss/index.xml", PoliticalBias.CENTER),
    BBC("https://news.bbcimg.co.uk/nol/shared/img/bbc_news_120x60.gif", "http://feeds.bbci.co.uk/news/rss.xml", PoliticalBias.CENTER),
    PRO_PUBLICA("https://assets.propublica.org/propublica-rss-logo.png", "https://www.propublica.org/feeds/propublica/main", PoliticalBias.CENTER),
    THE_ECONOMIST("https://en.wikipedia.org/wiki/The_Economist#/media/File:The_Economist_Logo.svg", "https://www.economist.com/the-world-this-week/rss.xml", PoliticalBias.CENTER),
    POLITICO("https://i1.feedspot.com/150897.jpg?t=1603896032", "https://rss.politico.com/politics-news.xml", PoliticalBias.CENTER),
    NPR("https://i1.feedspot.com/20253.jpg?t=1603896038", "https://www.reutersagency.com/feed/?taxonomy=best-sectors&post_type=best", PoliticalBias.CENTER),
    ROUTERS("https://www.reutersagency.com/wp-content/uploads/2019/06/fav-150x150.png", "https://www.reutersagency.com/feed/?taxonomy=best-sectors&post_type=best", PoliticalBias.CENTER),
    ABC("https://s.abcnews.com/images/site/abcnews_google_rss_logo.png", "https://abcnews.go.com/abcnews/topstories", PoliticalBias.CENTER_RIGHT),
    CBS("", "https://www.cbsnews.com/latest/rss/main", PoliticalBias.CENTER_RIGHT),
    CNN("https://i1.feedspot.com/117.jpg?t=1603895993", "http://rss.cnn.com/rss/cnn_topstories.rss", PoliticalBias.CENTER_RIGHT),
    THE_NEW_YORK_TIMES("https://i1.feedspot.com/9470.jpg?t=1603896012", "https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml", PoliticalBias.CENTER_RIGHT),
    WASHINGTON_POST_NATIONAL("https://i1.feedspot.com/337297.jpg", "https://feeds.washingtonpost.com/rss/national", PoliticalBias.CENTER_RIGHT),
    WASHINGTON_POST_WORLD("https://i1.feedspot.com/337297.jpg", "https://feeds.washingtonpost.com/rss/world", PoliticalBias.CENTER_RIGHT),
    FOX("https://i1.feedspot.com/118508.jpg?t=1603896019", "http://feeds.foxnews.com/foxnews/latest", PoliticalBias.RIGHT),
    NEWSMAX("https://www.newsmax.com/App_Themes/News-max-mobile/appicon.png", "https://www.newsmax.com/rss/Newsfront/16/", PoliticalBias.RIGHT),
    BREITBART("https://i1.feedspot.com/4210776.jpg?t=1603896056", "http://feeds.feedburner.com/breitbart", PoliticalBias.RIGHT),
    THE_BLAZE("https://assets.rebelmouse.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpbWFnZSI6Imh0dHBzOi8vYXNzZXRzLnJibC5tcy8xODkyNDQyMS8yMDAweC5wbmciLCJleHBpcmVzX2F0IjoxNjkzNTA2MTM3fQ.DqI7JpT5nhiQz_GxD1VrHiiqoKCSSkgiVMJKBNQvVAQ/img.png?width=32&height=32", "https://www.theblaze.com/feeds/feed.rss", PoliticalBias.RIGHT),
    THE_AMERICAN_CONSERVATIVE("https://www.theamericanconservative.com/wp-content/uploads/2021/08/cropped-tac_favicon-32x32.jpg", "https://www.theamericanconservative.com/articles/feed/", PoliticalBias.RIGHT),
    OAN("https://www.oann.com/wp-content/uploads/2021/09/cropped-favicon-32x32.jpg", "https://www.oann.com/feed/", PoliticalBias.RIGHT),
    THE_AMERICAN_SPECTATOR("https://spectator.org/wp-content/uploads/2019/03/cropped-favicon-150x150.png", "https://spectator.org/feed/", PoliticalBias.RIGHT),
}