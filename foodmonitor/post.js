function main(params) {
  return new Promise(function (resolve, reject) {
    const Cloudant = require("@cloudant/cloudant");
    const cloudant = Cloudant({
      url:
        "https://7ae9ef74-43a4-45d4-a1a7-b22f2b9a429b-bluemix:6462636a5a6617432f81ac567e917a323e8b5ac2c25f2f0826b4683a3039827c@7ae9ef74-43a4-45d4-a1a7-b22f2b9a429b-bluemix.cloudantnosqldb.appdomain.cloud",
      plugins: {
        iamauth: { iamApiKey: "admin" },
      },
    });

    const fooddb = cloudant.use("food-details-db");
    fooddb
      .find({ selector: {}, fields: ["_id", "_rev", "image", "status"] })
      .then((result) => {
        var obj = result.docs.find(
          (o) => o.status === params.status && o.uid === params.uid
        );
        var index = result.docs.indexOf(obj);
        console.log(index);
        if (index != -1) {
          function insert(trackingItem) {
            console.log(` ${JSON.stringify(trackingItem)}`);
            return fooddb.insert({
              _id: trackingItem._id,
              _rev: trackingItem._rev,
              image: params.__ow_body,
              status: params.status,
              uid: params.uid,
            });
          }
          return insert(result.docs[index]);
        } else {
          return fooddb.insert({
            image: params.__ow_body,
            status: params.status,
            uid: params.uid,
          });
        }
      })
      .then((result) => {
        var request = require("request");
        request.post(
          {
            url: "https://fcm.googleapis.com/fcm/send",
            headers: {
              "content-type": "application/json",
              authorization:
                "key=AAAAYLYdujs:APA91bE8o7eX_1VWDKQXWcwLS_zsxsT8D398i9YpeK8lU2m3uRn2paYYt-9INnkKFzs-PiW75CGZvHZLyT1R5aOBL-wvYhpxPui42vDDwj9SpWqa-nYP2Mctt_4yPlacBsCWPBCGAn-U",
            },
            json: {
              to: params.token,
              data: { status: params.status },
            },
          },
          function (error, response, body) {
            if (!error && response.statusCode == 200) {
              console.log(body); // your response body
            }
          }
        );
        console.log(result);
        resolve({
          statusCode: 200,
          headers: { "Content-Type": "application/json" },
          body: { success: "Tracking Details Updated.", id: result.id },
        });
      })
      .catch((err) => {
        console.log("final catch");
        reject({
          headers: { "Content-Type": "application/json" },
          statusCode: 500,
          body: { error: "Cat could not be updated." },
        });
      });
  });
}
