(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Views.MenuView = (function(_super) {

    __extends(MenuView, _super);

    function MenuView() {
      return MenuView.__super__.constructor.apply(this, arguments);
    }

    MenuView.prototype.el = '#menu';

    MenuView.prototype.initialize = function() {
      return this.render();
    };

    MenuView.prototype.render = function() {
      var notification;
      notification = [
        {
          title: 'item',
          message: 'bladieblad',
          id: 'not1'
        }, {
          title: 'item2',
          message: 'bladieblad2',
          id: 'not2'
        }
      ];
      $(this.el).html(Handlebars.templates['menuView']({
        'nrOfNoticifications': notification.length,
        'notifications': Handlebars.templates['notificationsView']({
          notifications: notification
        })
      }));
      return $('#notifications').popover();
    };

    return MenuView;

  })(Backbone.View);

}).call(this);
