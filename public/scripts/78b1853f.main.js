(function() {

  window.shuttledriveWeb = {
    Models: {},
    Collections: {},
    Views: {},
    Routers: {},
    Helpers: {},
    rootPath: 'http://145.33.225.224:8080',
    init: function() {
      new shuttledriveWeb.Views.MenuView();
      shuttledriveWeb.app = new shuttledriveWeb.Routers.ApplicationRouter();
      return Backbone.history.start();
    }
  };

  $(document).ready(function() {
    return shuttledriveWeb.init();
  });

}).call(this);
