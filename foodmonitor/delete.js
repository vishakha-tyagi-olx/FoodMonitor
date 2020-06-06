function main(params) {
    return new Promise(function (resolve, reject) {
      const Cloudant = require("@cloudant/cloudant");
      const cloudant = Cloudant({
        url:
          "https://7ae9ef74-43a4-45d4-a1a7-b22f2b9a429b-bluemix:6462636a5a6617432f81ac567e917a323e8b5ac2c25f2f0826b4683a3039827c@7ae9ef74-43a4-45d4-a1a7-b22f2b9a429b-bluemix.cloudantnosqldb.appdomain.cloud",   plugins: {
          iamauth: { iamApiKey: "admin" },
        },
      });
  
      const fooddb = cloudant.use("food-details-db");
      fooddb.find({ selector: {} }).then((result) => {
        var count = 0;
        setTimeout(function () {
    return resolve({
        status: "done"
    })
  }, 5000)
        result.docs
          .forEach(function (doc) {
            count = count++;
            fooddb.destroy(doc._id, doc._rev, function (err) {
              count == result.docs.length
                ? resolve({ status: "done" })
                : console.log("");
            });
          });
          
      });
    });
  }
  
  